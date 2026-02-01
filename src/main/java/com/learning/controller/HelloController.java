package com.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot! 👋 Welcome to your first Spring Boot application!";
    }

    @GetMapping("/hello/{name}")
    public String sayHelloToName(@PathVariable String name) {
        return "Hello, " + name + "! 🎉 Nice to meet you!";
    }

    @GetMapping("/greet")
    public String greet(
            @RequestParam(defaultValue = "World") String name,
            @RequestParam(defaultValue = "Hello") String greeting) {
        return greeting + ", " + name + "! 🌟";
    }
}
