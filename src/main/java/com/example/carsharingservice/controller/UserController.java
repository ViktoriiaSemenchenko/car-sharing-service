package com.example.carsharingservice.controller;

import com.example.carsharingservice.dto.request.UserRequestDto;
import com.example.carsharingservice.dto.response.UserResponseDto;
import com.example.carsharingservice.model.User;
import com.example.carsharingservice.service.UserService;
import com.example.carsharingservice.service.mapper.DtoMapper;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/***
 * Managing authentication and user registration
 * API:
 * PUT:              users/{id}/role              - update user role
 * GET:              users/me/                    - get my profile info
 * PUT/PATCH: users/me/                    - update profile info
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final DtoMapper<UserRequestDto, UserResponseDto, User> userDtoMapper;

    public UserController(UserService userService,
                          DtoMapper<UserRequestDto, UserResponseDto, User> userResponseDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userResponseDtoMapper;
    }

    @PutMapping("/{id}/role")
    public UserResponseDto update(@PathVariable Long id,
                                  @RequestBody @Valid UserRequestDto requestDto) {
        User user = userDtoMapper.toModel(requestDto);
        user.setId(id);
        userService.updateUserRole(id, User.Role.valueOf(requestDto.getRole()));
        return userDtoMapper.toDto(user);
    }

    @GetMapping("/me")
    public UserResponseDto get(Authentication auth) {
        String currentUsername = auth.getName();
        User currentUser = userService.getUserByUsername(currentUsername);
        return userDtoMapper.toDto(currentUser);
    }

    @PatchMapping("/me")
    public UserResponseDto updateProfile(@RequestBody @Valid UserRequestDto requestDto, Authentication auth) {
        User user = userDtoMapper.toModel(requestDto);
        user = userService.getUserByUsername(auth.getName());
        return userDtoMapper.toDto(userService.save(user));
    }
}
