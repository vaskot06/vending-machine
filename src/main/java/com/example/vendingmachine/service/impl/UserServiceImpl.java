package com.example.vendingmachine.service.impl;

import com.example.vendingmachine.enums.Coins;
import com.example.vendingmachine.model.dtos.CoinDto;
import com.example.vendingmachine.model.dtos.UserDto;
import com.example.vendingmachine.model.entity.Product;
import com.example.vendingmachine.model.entity.User;
import com.example.vendingmachine.repositories.ProductRepository;
import com.example.vendingmachine.repositories.UserRepository;
import com.example.vendingmachine.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public void createUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        userRepository.save(user);
    }

//    @Override
//    public void updateUser(UserDto userDto) {
//        User user = userRepository.findById(userDto.getId()).orElseThrow();
//        user.setType(userDto.getType());
//        user.setPrice(userDto.getPrice());
//        userRepository.save(user);
//    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void insertCoins(Long id, CoinDto coin) throws Exception {
        User user = userRepository.findById(id).orElseThrow();

        boolean isCoinNotValid = Arrays.stream(Coins.values()).noneMatch(c -> c.getValue().compareTo(coin.getCoin()) ==0 );

        if (isCoinNotValid){
            throw new Exception("Coin is not valid!");
        }


        BigDecimal coins = user.getInsertedCoins().add(coin.getCoin());
        user.setInsertedCoins(coins);
        userRepository.save(user);
    }

    @Override
    public String checkMachineState(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        List<Product> products = productRepository.findAll();

        StringBuilder builder = new StringBuilder();

        builder.append("Current user is: ").append(user.getId()).append("\n");

        products.forEach(p -> builder.append("Product price is ")
                .append(p.getPrice())
                .append(" and availability is ")
                .append(p.getAvailable())
                .append("\n"));

        return builder.toString();
    }
}
