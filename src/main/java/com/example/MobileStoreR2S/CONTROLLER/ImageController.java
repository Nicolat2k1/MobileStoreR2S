package com.example.MobileStoreR2S.CONTROLLER;

import com.example.MobileStoreR2S.SERVICE.ImageSV;
import org.springframework.web.bind.annotation.RestController;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/image")

public class ImageController {
    private final ImageSV imageService;

    public ImageController(ImageSV imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<?> getAllImages() {
        try {
            return ResponseEntity.ok(imageService.findAll());

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(imageService.findById(id));

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
