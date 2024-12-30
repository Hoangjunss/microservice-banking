package com.banking.account.service.auth;

import com.banking.account.dto.AuthDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AuthService {
    List<AuthDTO> getAllAuths();
    AuthDTO getAuthById(Integer id);
    AuthDTO createAuth(AuthDTO authDTO);
    AuthDTO updateAuth(Integer id, AuthDTO authDTO);
    void deleteAuth(Integer id);
}
