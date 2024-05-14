package com.example.MobileStoreR2S.SERVICE;

import java.util.List;

import com.example.MobileStoreR2S.DTO.CategoryDTO;

public interface CategorySV {
    CategoryDTO create(CategoryDTO categoryDTO);

    List<CategoryDTO> findAll();

    CategoryDTO findById(long id);

    CategoryDTO update(long id, CategoryDTO categoryDTO);

    void delete(long id);
}
