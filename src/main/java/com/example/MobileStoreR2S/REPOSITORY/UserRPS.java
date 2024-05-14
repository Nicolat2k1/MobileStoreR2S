package com.example.MobileStoreR2S.REPOSITORY;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MobileStoreR2S.ENTITY.User;

@Repository
public interface UserRPS extends JpaRepository<User, Long>{
    public boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}

