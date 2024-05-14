package com.example.MobileStoreR2S.MAPPER;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.example.MobileStoreR2S.DTO.OrderDTO;
import com.example.MobileStoreR2S.ENTITY.Order;

@Mapper(componentModel = "spring")
@Component
public interface OrderMP {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "orderDetailDTOs", source = "orderDetails")
    OrderDTO toDTO(Order order);

    @Mapping(target = "user.id",source = "userId")
    @Mapping(target = "orderDetails",source = "orderDetailDTOs")
    Order toEntity(OrderDTO orderDTO);
}