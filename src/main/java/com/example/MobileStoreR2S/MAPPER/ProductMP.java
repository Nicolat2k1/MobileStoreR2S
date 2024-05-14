package com.example.MobileStoreR2S.MAPPER;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.example.MobileStoreR2S.DTO.ProductDTO;
import com.example.MobileStoreR2S.ENTITY.Product;

@Mapper(componentModel = "spring")
@Component
public interface ProductMP {

    @Mapping(source = "category.id", target = "category")
    @Mapping(source = "images", target = "imageDTOs")
    ProductDTO toDTO(Product product);

    @Mapping(source = "category", target = "category.id")
    @Mapping(source = "imageDTOs", target = "images")
    Product toEntity(ProductDTO productDTO);
}
