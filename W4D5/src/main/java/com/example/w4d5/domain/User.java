package com.example.w4d5.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class User {
    private Integer userId;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private Boolean activate;
}
