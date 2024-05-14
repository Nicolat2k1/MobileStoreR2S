package com.example.MobileStoreR2S.MAPPER;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.example.MobileStoreR2S.DTO.OrderDetailDTO;
import com.example.MobileStoreR2S.ENTITY.OrderDetail;

@Mapper(componentModel = "spring")
@Component
public interface OrderDetailMP {
    @Mapping(target = "id", source = "orderDetail.id")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "orderId", source = "order.id")
    OrderDetailDTO toDTO(OrderDetail orderDetail);

    @Mapping(target = "order.id", source = "id")
    @Mapping(target = "product.id", source = "productId")
        // @Mapping(target = "order.id", source = "orderId")
    OrderDetail toEntity(OrderDetailDTO orderDetailDTO);
}