package com.example.carsharingservice.controller;

import com.example.carsharingservice.dto.request.UserRequestDto;
import com.example.carsharingservice.dto.response.UserResponseDto;
import com.example.carsharingservice.model.User;
import com.example.carsharingservice.service.UserService;
import com.example.carsharingservice.service.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    @PutMapping("/{id}/role")
    public UserResponseDto updateRole(@PathVariable Long id, @RequestBody String role) {
        return mapper.toDto(userService.updateUserRole(id, User.Role.valueOf(role)));
    }

    @GetMapping("/me")
    public UserResponseDto get(Authentication auth) {
        String email = auth.getName();
        User currentUser = userService.getByEmail(email);
        return mapper.toDto(currentUser);
    }

    @PatchMapping("/me")
    public UserResponseDto updateProfile(@RequestBody @Valid UserRequestDto requestDto,
                                         Authentication auth) {
        User user = userService.getByEmail(auth.getName());
        user.setEmail(requestDto.getEmail());
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setLastName(requestDto.getRole());
        return mapper.toDto(userService.save(user));
    }
}
