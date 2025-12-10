package com.na3t1e.secure_api.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
