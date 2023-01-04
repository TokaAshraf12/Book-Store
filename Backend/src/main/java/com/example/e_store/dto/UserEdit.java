package com.example.e_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEdit {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
}
