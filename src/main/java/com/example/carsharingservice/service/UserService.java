package com.example.carsharingservice.service;

import com.example.carsharingservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    User save(User user);

    User updateUserRole(Long userId, User.Role newRole);

    User getUserByFirstName(String firstName);

    User getByEmail(String email);
}
