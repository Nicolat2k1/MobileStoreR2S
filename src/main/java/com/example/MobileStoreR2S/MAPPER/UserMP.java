package com.example.MobileStoreR2S.MAPPER;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.example.MobileStoreR2S.DTO.RegisterDTO;
import com.example.MobileStoreR2S.DTO.UserDTO;
import com.example.MobileStoreR2S.ENTITY.User;

@Mapper(componentModel = "spring")
@Component
public interface UserMP {
    @Mapping(target = "role", source = "role.id")
    RegisterDTO toDTO(User user);

    @Mapping(target = "role.id", source = "role")
    User toEntity(RegisterDTO registerDTO);

    @Mapping(target = "role", source = "role.id")
    UserDTO toUserDTO(User user);

    @Mapping(target = "role.id", source = "role")
    User toUserEntity(UserDTO userDTO);
}

