package com.example.MobileStoreR2S.DTO;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDTO {
    private long id;

    private String username;

    private String password;

    private LocalDateTime createAt;

    private Long role;
}