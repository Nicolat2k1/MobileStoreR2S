package com.example.MobileStoreR2S.DTO;

import lombok.Data;

@Data
public class ResponseLoginDTO {
    private String username;
    private String token;
    private String role;
}
