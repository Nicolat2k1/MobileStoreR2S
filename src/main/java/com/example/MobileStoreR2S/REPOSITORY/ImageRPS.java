package com.example.MobileStoreR2S.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.MobileStoreR2S.ENTITY.Image;

@Repository
public interface ImageRPS extends JpaRepository<Image, Long>{
    @Query("SELECT i FROM Image i WHERE i.product.id = ?1")
    List<Image> findByProductId(Long productId);
}
