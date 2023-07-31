package com.example.carsharingservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    @Email
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
}
