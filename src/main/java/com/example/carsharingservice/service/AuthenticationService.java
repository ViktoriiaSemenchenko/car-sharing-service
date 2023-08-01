package com.example.carsharingservice.service;

import com.example.carsharingservice.model.User;

public interface AuthenticationService {
    User register(String email, String password, String firstName, String lastName);

    boolean authenticate(String email, String password);
}
