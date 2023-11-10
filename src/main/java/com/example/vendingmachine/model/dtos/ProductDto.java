package com.example.vendingmachine.model.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String type;
    private BigDecimal price;
    private Boolean available;
}
