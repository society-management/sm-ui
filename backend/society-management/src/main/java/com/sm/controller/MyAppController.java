package com.sm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class MyAppController {

    @GetMapping("/")
    public String welcome() {
        return String.valueOf(LocalDateTime.now());
    }
}
