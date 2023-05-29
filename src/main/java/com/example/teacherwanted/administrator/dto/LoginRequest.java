package com.example.teacherwanted.administrator.dto;

import jakarta.persistence.Column;

public class LoginRequest {
    @Column(name = "admin_account")
    private String adminAccount;
    @Column(name = "admin_password")
    private String adminPassword;
}
