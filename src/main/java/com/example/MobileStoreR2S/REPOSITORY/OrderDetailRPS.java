package com.example.MobileStoreR2S.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.MobileStoreR2S.ENTITY.OrderDetail;

public interface OrderDetailRPS extends JpaRepository<OrderDetail, Long> {
    @Query("SELECT o FROM OrderDetail o WHERE o.id = ?1")
    List<OrderDetail> findByOrderDetailId(Long orderDetailId);
}