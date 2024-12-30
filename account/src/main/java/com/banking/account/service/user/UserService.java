package com.banking.account.service.user;

import com.banking.account.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Integer id);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Integer id, UserDTO userDTO);
    void deleteUser(Integer id);
}
