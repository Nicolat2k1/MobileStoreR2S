package com.example.MobileStoreR2S.DTO;


import java.util.List;

import lombok.Data;

@Data
public class ProductDTO {
    private long id;

    private String name;

    private Double Unitprice;

    private long unitStock;

    private String description;

    private String manufacturer;

    private long category;

    private String condition;

    private List<ImageDTO> imageDTOs;
}
