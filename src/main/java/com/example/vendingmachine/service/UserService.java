package com.example.vendingmachine.service;

import com.example.vendingmachine.model.dtos.CoinDto;
import com.example.vendingmachine.model.dtos.UserDto;

public interface UserService {
    void createUser (UserDto userDto);

//    void updateUser (UserDto userDto);

    void deleteUser (Long id);

    void insertCoins(Long id, CoinDto coin) throws Exception;

    String checkMachineState(Long id);
}
