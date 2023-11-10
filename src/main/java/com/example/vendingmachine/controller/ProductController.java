package com.example.vendingmachine.controller;

import com.example.vendingmachine.model.dtos.ProductUserBuyDto;
import com.example.vendingmachine.model.dtos.ProductDto;
import com.example.vendingmachine.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) throws Exception {

        productService.createProduct(productDto);
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto){

        productService.updateProduct(productDto);
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable Long id){

        productService.deleteProduct(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyProduct(@RequestBody ProductUserBuyDto ids) throws Exception {

        productService.buyProduct(ids);
        String message = productService.returnChange(ids);

        if (!message.isEmpty()){
            return ResponseEntity.ok("Product bought successfully! " + message);
        }

        return ResponseEntity.ok("Product bought successfully!");
    }

}
