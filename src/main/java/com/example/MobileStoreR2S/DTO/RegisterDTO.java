package com.example.MobileStoreR2S.DTO;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;

    private String password;

    private String email;

    private String fullname;

    private Long role;

    private LocalDateTime createAt;


}