package com.example.e_store.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Email
    @Column(unique = true, nullable = false, length = 80)
    @NotEmpty(message = "Email is required")
    private String email;
    @Column(nullable = false, length = 50)
    @NotBlank(message = "First Name is required")
    private String firstName;
    @Column(nullable = false, length = 50)
    @NotBlank(message = "Last Name is required")
    private String lastName;
    @Column(nullable = false, name = "encodedPassword", length = 65)
    @NotBlank(message = "Password is required")
    private String password;
    @Column(nullable = false, length = 15)
    @NotBlank(message = "Date of Birth is required")
    private String dateOfBirth;
    @Column(nullable = false, length = 15)
    @NotBlank(message = "Phone Number is required")
    private String phoneNumber;
    @Column(nullable = false, length = 20)
    @NotBlank(message = "Gender is required")
    private String gender;
    @NotNull(message = "Specify System User")
    private Boolean isManager;
}
