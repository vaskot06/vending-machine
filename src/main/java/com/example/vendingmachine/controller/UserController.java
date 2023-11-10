package com.example.vendingmachine.controller;

import com.example.vendingmachine.model.dtos.CoinDto;
import com.example.vendingmachine.model.dtos.UserDto;
import com.example.vendingmachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws Exception {

        userService.createUser(userDto);
        return ResponseEntity.ok(userDto);
    }

//    @PutMapping("/update")
//    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
//
//        userService.updateUser(userDto);
//        return ResponseEntity.ok(userDto);
//    }

    @PostMapping("/insert-coins/{id}")
    public ResponseEntity insertCoins(@PathVariable Long id, @RequestBody CoinDto coin) throws Exception {

        userService.insertCoins(id, coin);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/check-status/{id}")
    public ResponseEntity<String> checkMachineState(@PathVariable Long id){

        String machineState = userService.checkMachineState(id);
        return ResponseEntity.ok(machineState);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id){

        userService.deleteUser(id);
        return ResponseEntity.ok(id);
    }

}
