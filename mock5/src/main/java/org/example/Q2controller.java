package org.example;

import lombok.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.io.Serializable;

@RestController
public class Q2controller {

    @GetMapping("users/{id}")
    public ResponseEntity<User> register(@PathVariable Integer id) throws JsonProcessingException {
        String uri = "https://jsonplaceholder.typicode.com/users/" + id;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        final ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(result, User.class);

        return ResponseEntity.ok(user);

    }

}


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class User implements Serializable {
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
@AllArgsConstructor
@NoArgsConstructor
class Address implements Serializable {
    String street;
    String suite;
    String city;
    String zipcode;
    Geo geo;
}

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Geo implements Serializable {
    String lat;
    String lng;
}

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Company implements Serializable {
    String name;
    String catchPhrase;
    String bs;
}


