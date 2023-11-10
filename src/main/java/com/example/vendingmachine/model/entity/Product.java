package com.example.vendingmachine.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "product")
public class Product extends BaseModel{

    @Column
    private String type;
    @Column
    private BigDecimal price;
    @Column
    private Boolean available;
    @ManyToMany(mappedBy = "productsBought")
    private List<User> user;
}