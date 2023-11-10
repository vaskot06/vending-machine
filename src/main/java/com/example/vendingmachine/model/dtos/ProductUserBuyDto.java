package com.example.vendingmachine.model.dtos;

import lombok.Data;

@Data
public class ProductUserBuyDto {
    private Long userId;
    private Long productId;
}
