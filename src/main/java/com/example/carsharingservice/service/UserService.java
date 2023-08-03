package com.example.carsharingservice.service;

import com.example.carsharingservice.model.User;

public interface UserService {
    User save(User user);

    User updateUserRole(Long userId, User.Role newRole);

    User getUserByFirstName(String firstName);

    User getByEmail(String email);

    User getById(Long id);
}
