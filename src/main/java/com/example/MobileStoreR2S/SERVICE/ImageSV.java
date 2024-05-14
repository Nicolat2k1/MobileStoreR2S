package com.example.MobileStoreR2S.SERVICE;

import java.util.List;

import com.example.MobileStoreR2S.DTO.ImageDTO;

public interface ImageSV {
    List<ImageDTO> findAll();

    ImageDTO findById(long id);
}
