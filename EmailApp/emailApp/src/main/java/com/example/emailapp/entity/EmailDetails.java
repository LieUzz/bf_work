package com.example.emailapp.entity;


// Java Program to Illustrate EmailDetails Class

// Importing required classes
import lombok.*;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
// Class
public class EmailDetails {

    // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
