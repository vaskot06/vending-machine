package com.example.vendingmachine.service;

import com.example.vendingmachine.model.dtos.ProductDto;
import com.example.vendingmachine.model.dtos.ProductUserBuyDto;
import com.example.vendingmachine.model.entity.Product;

public interface ProductService {

    Product getProductById(Long id);

    void createProduct (ProductDto productDto) throws Exception;

    void updateProduct (ProductDto productDto);

    void deleteProduct (Long id);

    void buyProduct(ProductUserBuyDto ids) throws Exception;

    String returnChange(ProductUserBuyDto ids);
}
