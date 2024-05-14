package com.example.MobileStoreR2S.SERVICE;

import java.util.List;

import com.example.MobileStoreR2S.DTO.ProductDTO;

public interface ProductSV {
    List<ProductDTO> findAll();

    ProductDTO findById(long id);

    ProductDTO create(ProductDTO productDTO);

    ProductDTO update(long id, ProductDTO productDTO);

    void delete(long id);
}
