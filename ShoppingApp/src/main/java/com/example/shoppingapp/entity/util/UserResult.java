package com.example.shoppingapp.entity.util;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@EqualsAndHashCode
public class UserResult {
    Integer id;
    String username;
    String email;
}
