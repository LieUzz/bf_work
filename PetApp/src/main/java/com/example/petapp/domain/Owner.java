package com.example.petapp.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Owner {
    String id;
    String name;
    List<Pet> pets;
}
