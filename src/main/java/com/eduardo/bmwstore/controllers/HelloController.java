package com.eduardo.bmwstore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("path")
    public String getMethodName() {
        return new String("123");
    }
    

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
  
}