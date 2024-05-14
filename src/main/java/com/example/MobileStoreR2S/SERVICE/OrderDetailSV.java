package com.example.MobileStoreR2S.SERVICE;

import java.util.List;

import com.example.MobileStoreR2S.DTO.OrderDetailDTO;

public interface OrderDetailSV {
    List<OrderDetailDTO> findAll();

    OrderDetailDTO findById(long id);
}
