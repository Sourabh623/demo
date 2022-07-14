package com.example.demo.model;

import com.example.demo.configuration.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotEmpty(message = "firstname field is required")
    @Size(max = 20, min = 3, message = "firstname must be greater then 3 char")
    private String firstName;

    @NotEmpty(message = "lastname field is required")
    @Size(min = 3, message = "lastname must be greater then 3 char")
    private String lastName;

    @NotEmpty(message = "email field is required")
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String userEmail;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z](?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}[a-zA-Z0-9@#$%^&+=]$", message = AppConstants.password)
    private String password;

    private Date creationDate;
    private Date updationDate;
}
