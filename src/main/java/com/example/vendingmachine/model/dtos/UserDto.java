package com.example.vendingmachine.model.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDto {
    private Long id;
    private BigDecimal insertedCoins;
    private String name;
}
