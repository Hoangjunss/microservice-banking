package com.banking.account.mapper;

import com.banking.account.dto.AuthDTO;
import com.banking.account.entity.Auth;

public class AuthMapper {
    public static AuthDTO toDTO(Auth auth) {
        AuthDTO dto = new AuthDTO();
        dto.setId(auth.getId());
        dto.setEmail(auth.getEmail());
        dto.setPassword(auth.getPassword());
        dto.setAccountId(auth.getAccount().getId());
        return dto;
    }

    public static Auth toEntity(AuthDTO dto) {
        Auth auth = new Auth();
        auth.setId(dto.getId());
        auth.setEmail(dto.getEmail());
        auth.setPassword(dto.getPassword());
        // You will need to fetch the Account entity by its id
        // auth.setAccount(account);
        return auth;
    }
}
