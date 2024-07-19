package com.dreamgenics.spring.datajpa.controller;

import com.dreamgenics.spring.datajpa.services.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/api/imgBackSide")
@RestController
public class ImgController {

    @Autowired
    private ImgService imgService;


    @GetMapping
    public ResponseEntity<String> getImage(@RequestParam String query) {
        String imageUrl = imgService.fetchImageUrlFromPixabay(query);
        if (imageUrl != null) {
            return ResponseEntity.ok(imageUrl);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
        }
    }

}
