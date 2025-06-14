package com.cibersecurity.login.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String role;
    private LocalDateTime lastLogin;
}
