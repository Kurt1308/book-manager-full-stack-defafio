package com.bookmanager.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/")
    public String home() {
        return "BookManager API funcionando!";
    }


    @GetMapping("/status")
    public String status() {
        return "Backend online";
    }
}