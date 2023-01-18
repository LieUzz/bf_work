package com.example.w4d5.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // exclude null fields
public class UserResponse {
    ResponseStatus status;
    User user;
}
