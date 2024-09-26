package com.example.learning_dev_2024.payload.response;

import lombok.Data;

@Data
public class TokenResponse {
    private String token;
    private String tokenType ;

    public TokenResponse(String token) {
        this.token = token;
        this.tokenType = "Bearer";
    }
}
