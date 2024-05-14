package com.example.MobileStoreR2S.MAPPER;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.MobileStoreR2S.DTO.RoleDTO;
import com.example.MobileStoreR2S.ENTITY.Role;

@Mapper(componentModel = "spring")
@Component
public interface RoleMP {
    Role toEntity(RoleDTO roleDTO);

    RoleDTO toDTO(Role role);
}

