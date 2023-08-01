package com.example.carsharingservice.service.impl;

import com.example.carsharingservice.model.User;
import com.example.carsharingservice.service.AuthenticationService;
import com.example.carsharingservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;

    @Override
    public User register(String email, String password, String firstName, String lastName) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(User.Role.CUSTOMER);
        return userService.save(user);
    }

    @Override
    public boolean authenticate(String email, String password) {
        User user;
        try {
            user = userService.getByEmail(email);
            if (password.equals(user.getPassword())) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Incorrect username or password");
        }
    }
}
