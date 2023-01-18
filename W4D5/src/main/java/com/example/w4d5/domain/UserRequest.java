package com.example.w4d5.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
public class UserRequest {

    @NotNull
    private Integer userId;

    @NotNull(message = "Username is required") // it will accept " " ?
    @Size(min = 5, max = 13, message = "Username must be between 6 and 13 characters")
    private String username;

    @NotNull(message = "Password is required") // it will accept " " ?
    @Size(min = 5, max = 13, message = "Password must be between 8 and 13 characters")
    private String password;
    @NotNull(message = "Firstname is required") // it will accept " " ?
    @Size(min = 2, max = 13, message = "Firstname must be between 6 and 13 characters")
    private String firstname;
    @NotNull(message = "Lastname is required") // it will accept " " ?
    @Size(min = 2, max = 13, message = "Lastname must be between 6 and 13 characters")
    private String lastname;

    @NotNull(message = "Activate is required")
    private Boolean activate;
}

