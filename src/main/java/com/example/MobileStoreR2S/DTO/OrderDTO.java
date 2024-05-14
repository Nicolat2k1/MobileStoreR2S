package com.example.MobileStoreR2S.DTO;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDTO {
    private long id; // Assuming ID is generated, not validated for blank

    @NotNull(message = "Total quantity is required")
    @Min(value = 1, message = "Total quantity must be at least 1")
    private long totalQuantity;

    @NotNull(message = "Grand total is required")
    @Min(value = 0, message = "Grand total must be non-negative")
    private Double grandTotal;

    @NotNull(message = "User ID is required")
    @Min(value = 1, message = "User ID must be a positive number")
    private long userId;

    @NotEmpty(message = "Order details are required")
    @Valid
    private List<OrderDetailDTO> orderDetailDTOs;
}
