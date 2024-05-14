package com.example.MobileStoreR2S.EXCEPTION;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Not found with id: " + id);
    }
}

