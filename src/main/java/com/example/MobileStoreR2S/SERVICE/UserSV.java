package com.example.MobileStoreR2S.SERVICE;

import java.util.List;
import java.util.Optional;

import com.example.MobileStoreR2S.DTO.LoginDTO;
import com.example.MobileStoreR2S.DTO.RegisterDTO;
import com.example.MobileStoreR2S.DTO.UserDTO;
import com.example.MobileStoreR2S.ENTITY.User;
import com.example.MobileStoreR2S.EXCEPTION.UserNotFoundException;
import com.example.MobileStoreR2S.EXCEPTION.NotFoundException;

public interface UserSV {
    RegisterDTO saveUser(RegisterDTO registerDTO) throws UserNotFoundException, NotFoundException;

    Optional<User> getUserByUsername(String username);

    List<UserDTO> getAllUser() throws NotFoundException;

    void deleteUserById(Long id);

    Object login(LoginDTO any);
}
