package com.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple Hello Controller
 * 
 * @RestController combines @Controller and @ResponseBody
 *                 It tells Spring that this class handles HTTP requests
 *                 and returns data directly (usually JSON) instead of view
 *                 templates.
 */
@RestController
public class HelloController {

    /**
     * Simple GET endpoint
     * 
     * @GetMapping maps HTTP GET requests to this method
     * 
     *             Try: http://localhost:8080/hello
     */
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot! 👋 Welcome to your first Spring Boot application!";
    }

    /**
     * GET with Path Variable
     * {name} is a path variable - part of the URL
     * 
     * Try: http://localhost:8080/hello/John
     */
    @GetMapping("/hello/{name}")
    public String sayHelloToName(@PathVariable String name) {
        return "Hello, " + name + "! 🎉 Nice to meet you!";
    }

    /**
     * GET with Request Parameter
     * Request parameters are passed as ?key=value in the URL
     * 
     * Try: http://localhost:8080/greet?name=Alice&greeting=Hi
     */
    @GetMapping("/greet")
    public String greet(
            @RequestParam(defaultValue = "World") String name,
            @RequestParam(defaultValue = "Hello") String greeting) {
        return greeting + ", " + name + "! 🌟";
    }
}
