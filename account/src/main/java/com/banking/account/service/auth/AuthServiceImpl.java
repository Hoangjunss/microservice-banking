package com.banking.account.service.auth;
import com.banking.account.dto.AuthDTO;
import com.banking.account.entity.Auth;
import com.banking.account.mapper.AuthMapper;
import com.banking.account.redis.RedisContants;
import com.banking.account.redis.RedisService;
import com.banking.account.redisson.RedisDistributedLocker;
import com.banking.account.redisson.RedisDistributedService;
import com.banking.account.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisDistributedService redisDistributedService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<AuthDTO> getAllAuths() {
        return authRepository.findAll().stream()
                .map(AuthMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthDTO getAuthById(Integer id) {
        return authRepository.findById(id)
                .map(AuthMapper::toDTO)
                .orElseThrow();
    }

    @Override
    public AuthDTO createAuth(AuthDTO authDTO) {
        Auth auth = AuthMapper.toEntity(authDTO);

        // check email exist
       if (isEmailExist(auth.getEmail())){
           //return exception
           return null;
       }
       auth.setPassword(passwordEncoder.encode(auth.getPassword()));

        return AuthMapper.toDTO(authRepository.save(auth));
    }

    @Override
    public AuthDTO updateAuth(Integer id, AuthDTO authDTO) {
        Auth auth = authRepository.findById(id)
                .orElseThrow();

        if (isEmailExist(auth.getEmail())){
            //return exception
            return null;
        }
        
        auth.setEmail(authDTO.getEmail());
        auth.setPassword(passwordEncoder.encode(auth.getPassword()));
        // Update other fields as needed
        Auth updatedAuth = authRepository.save(auth);
        return AuthMapper.toDTO(updatedAuth);
    }

    @Override
    public void deleteAuth(Integer id) {
        authRepository.deleteById(id);
    }
    private  boolean isEmailExist(String email){
        // check email exist redis ,if true return exception
        if(redisService.hashExists(RedisContants.EMAIL_EXIST,email)){
            return true;
        }

        //no email in redis , use distributed lock
        // create lock process with KEY
        RedisDistributedLocker locker = redisDistributedService.getDistributedLock("DISTRIBUTED_LOCK_EMAIL_EXIST"+email);
        try {
            // create lock
            boolean isLock = locker.tryLock(1, 5, TimeUnit.SECONDS);
            // return exception if timelimit
            if (!isLock) {
                return false;
            }
            // Get email exist redis
            if(redisService.hashExists(RedisContants.EMAIL_EXIST,email)){
                return true;
            }
            //no email in redis
            //get by db

            //emailExist == true
            boolean emailExist=authRepository.findByEmail(email).isPresent();
            if(emailExist){
                //set redis
                redisService.hashSet(RedisContants.EMAIL_EXIST,email,true);
                return true;
            }

            //emailExist==false
            return  false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {

            locker.unlock();
        }

    }
}
