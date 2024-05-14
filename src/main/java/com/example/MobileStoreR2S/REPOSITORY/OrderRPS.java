package com.example.MobileStoreR2S.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MobileStoreR2S.ENTITY.Order;

@Repository
public interface OrderRPS extends JpaRepository<Order, Long>{
}
