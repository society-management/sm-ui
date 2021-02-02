package com.sm.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyPasswordEncoder {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean isPasswordMatch(String oldPassword, String dbPassword) {
        return passwordEncoder.matches(oldPassword, dbPassword);
    }

    public void validatePassword(String oldPassword, String dbPassword) {
        if (isPasswordMatch(oldPassword, dbPassword) == Boolean.FALSE) {
            new Exception("Incorrect Password");
        }
    }
}
