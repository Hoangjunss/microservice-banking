package com.banking.account.service.auth;
import com.banking.account.dto.AuthDTO;
import com.banking.account.entity.Auth;
import com.banking.account.mapper.AuthMapper;
import com.banking.account.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

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
        Auth savedAuth = authRepository.save(auth);
        return AuthMapper.toDTO(savedAuth);
    }

    @Override
    public AuthDTO updateAuth(Integer id, AuthDTO authDTO) {
        Auth auth = authRepository.findById(id)
                .orElseThrow();
        auth.setEmail(authDTO.getEmail());
        auth.setPassword(authDTO.getPassword());
        // Update other fields as needed
        Auth updatedAuth = authRepository.save(auth);
        return AuthMapper.toDTO(updatedAuth);
    }

    @Override
    public void deleteAuth(Integer id) {
        authRepository.deleteById(id);
    }
}
