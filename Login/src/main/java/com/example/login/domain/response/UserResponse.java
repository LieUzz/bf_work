package com.example.login.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    int id;
    String name;
    String username;
    String email;
    Address address;
    String phone;
    String website;
    Company company;
}

@Getter
@Setter
@Builder
class Address {
    String street;
    String suite;
    String city;
    String zipCode;
    Geo geo;

}
@Getter
@Setter
@Builder
class Geo {
    String lat;
    String lng;
}
@Getter
@Setter
@Builder
class Company {
    String name;
    String catchPhrase;
    String bs;
}
