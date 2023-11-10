package com.example.vendingmachine.service.userService;

import com.example.vendingmachine.model.dtos.UserDto;
import com.example.vendingmachine.model.entity.Product;
import com.example.vendingmachine.model.entity.User;
import com.example.vendingmachine.repositories.ProductRepository;
import com.example.vendingmachine.repositories.UserRepository;
import com.example.vendingmachine.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testCheckMachineState() {

        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Product product1 = new Product();
        product1.setPrice(BigDecimal.valueOf(10.0));
        product1.setAvailable(true);

        Product product2 = new Product();
        product2.setPrice(BigDecimal.valueOf(20.0));
        product2.setAvailable(false);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        String result = userService.checkMachineState(userId);

        String expected = "Current user is: " + userId + "\n" +
                "Product price is 10.0 and availability is true\n" +
                "Product price is 20.0 and availability is false\n";

        assertEquals(expected, result);
    }

    @Test
    public void testCreateUser() {

        UserDto userDto = new UserDto();
        userDto.setName("test");
        userDto.setInsertedCoins(BigDecimal.ZERO);

        when(userRepository.save(any(User.class))).thenReturn(new User());

        userService.createUser(userDto);

        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));

    }
}
