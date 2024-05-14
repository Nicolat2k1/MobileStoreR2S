package com.example.MobileStoreR2S.SERVICE;

import java.util.List;

import com.example.MobileStoreR2S.DTO.OrderDTO;


public interface OrderSV {

    List<OrderDTO> findAll();

    OrderDTO findById(long id);

    OrderDTO create(OrderDTO orderDTO);

    void delete(long id);

}