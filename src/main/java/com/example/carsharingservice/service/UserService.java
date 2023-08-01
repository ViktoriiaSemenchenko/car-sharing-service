package com.example.carsharingservice.service;

import com.example.carsharingservice.model.User;

public interface UserService {
    User save(User user);

    User updateUserRole(Long userId, User.Role newRole);

    User getUserByUsername(String username);
}
