package com.example.shoppingapp.domain.response;

import com.example.shoppingapp.domain.ServiceStatus;
import com.example.shoppingapp.entity.util.UserResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Builder
public class UserResultResponse {
    ServiceStatus serviceStatus;
    List<UserResult> users;
}
