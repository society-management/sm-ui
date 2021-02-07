package com.sm.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sm")
public class Main {
    public static void main(String[] args) {
       // SpringApplication.run(Main.class, args);
        System.out.println("Hello World!!");
    }
}
