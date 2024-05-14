package com.example.MobileStoreR2S.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDetailDTO {
    private long id;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private long quantity;

    @NotNull(message = "Unit price is required")
    @Min(value = 0, message = "Unit price must be non-negative") // Allow 0 for special cases (e.g., promotions)
    private double unitPrice;

    // Often, the 'price' is calculated, so no direct validation is needed here.
    private double price;

    @NotNull(message = "Order ID is required")
    @Min(value = 1, message = "Order ID must be a positive number")
    private long orderId;

    @NotNull(message = "Product ID is required")
    @Min(value = 1, message = "Product ID must be a positive number")
    private long productId;
}

