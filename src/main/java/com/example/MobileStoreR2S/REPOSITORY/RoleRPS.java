package com.example.MobileStoreR2S.REPOSITORY;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MobileStoreR2S.ENTITY.Role;

@Repository
public interface RoleRPS extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}

