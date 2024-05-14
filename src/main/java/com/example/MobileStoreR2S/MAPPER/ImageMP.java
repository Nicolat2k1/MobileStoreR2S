package com.example.MobileStoreR2S.MAPPER;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.example.MobileStoreR2S.DTO.ImageDTO;
import com.example.MobileStoreR2S.ENTITY.Image;

@Mapper(componentModel = "spring")
@Component
public interface ImageMP {
    @Mapping(target = "id", source = "image.id")
    ImageDTO toDTO(Image image);

    @Mapping(target = "product.id", source = "id")
    Image toEntity(ImageDTO imageDTO);
}
