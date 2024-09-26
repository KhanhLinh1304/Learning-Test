package com.example.learning_dev_2024.payload.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
