package com.example.petapp.domain;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pet {
    String id;
    String name;
    String species;
}
