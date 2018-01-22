package com.pramati.ts.aad.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping(value = "/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Spring Boot Azure TS SAAS is up & running!!");
    }
    
    @RequestMapping("/hello")
    public String home(){
        return "Hello World!";
    }
}
