package com.example.vendingmachine.service.impl;

import com.example.vendingmachine.model.dtos.ProductDto;
import com.example.vendingmachine.model.dtos.ProductUserBuyDto;
import com.example.vendingmachine.model.entity.Product;
import com.example.vendingmachine.model.entity.User;
import com.example.vendingmachine.repositories.ProductRepository;
import com.example.vendingmachine.repositories.UserRepository;
import com.example.vendingmachine.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public void createProduct(ProductDto productDto) throws Exception {
        Product product = this.modelMapper.map(productDto, Product.class);
        List<Product> products = productRepository.getProductsByType(product.getType());

        if (products.size() > 10){
            throw new Exception("Size cannot be greater than 10!");
        }

        productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow();
        product.setType(productDto.getType());
        product.setPrice(productDto.getPrice());
        product.setAvailable(productDto.getAvailable());
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void buyProduct(ProductUserBuyDto ids) throws Exception {
        Product product = productRepository.findById(ids.getProductId()).orElseThrow();
        User user = userRepository.findById(ids.getUserId()).orElseThrow();

        if (!product.getAvailable()){
            throw new Exception("Product unavailable!");
        }

        if (user.getInsertedCoins().compareTo(product.getPrice()) < 0){
            throw new Exception("Not enough money!");
        }

        List<Product> productsBought = user.getProductsBought() == null ? new ArrayList<>() : user.getProductsBought();

        productsBought.add(product);

        user.setProductsBought(productsBought);
        user.setInsertedCoins(user.getInsertedCoins().subtract(product.getPrice()));

        productRepository.save(product);
        userRepository.save(user);
    }

    @Override
    public String returnChange(ProductUserBuyDto ids) {
        User user = userRepository.findById(ids.getUserId()).orElseThrow();
        BigDecimal coins = user.getInsertedCoins();

        if (coins.equals(BigDecimal.ZERO)){
            return "";
        }

        if (user.getInsertedCoins().compareTo(BigDecimal.ZERO) != 0){
            user.setInsertedCoins(BigDecimal.ZERO);
        }
        return "Returned " + coins + " as change!";
    }

}
