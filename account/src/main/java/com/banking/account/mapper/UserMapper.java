package com.banking.account.mapper;

import com.banking.account.dto.UserDTO;
import com.banking.account.entity.User;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setPhone(user.getPhone());
        dto.setCmnd(user.getCmnd());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setPhone(dto.getPhone());
        user.setCmnd(dto.getCmnd());
        return user;
    }
}
