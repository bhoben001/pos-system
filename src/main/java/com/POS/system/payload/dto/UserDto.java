package com.POS.system.payload.dto;

import com.POS.system.Domain.UserRole;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({"id","fullName","email","phone","role","createdAt","updatedAt","lastLogin"})

public class UserDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;

    private String password;

    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
}

