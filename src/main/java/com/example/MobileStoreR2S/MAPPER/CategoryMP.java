package com.example.MobileStoreR2S.MAPPER;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.MobileStoreR2S.DTO.CategoryDTO;
import com.example.MobileStoreR2S.ENTITY.Category;

@Mapper(componentModel = "spring")
@Component
public interface CategoryMP {
    Category toEntity(CategoryDTO categoryDTO);

    CategoryDTO toDTO(Category category);
}