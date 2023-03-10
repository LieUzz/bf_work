package com.example.shoppingapp.domain.response;


import com.example.shoppingapp.domain.ServiceStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageResponse {
    ServiceStatus serviceStatus;
    String message;
}
