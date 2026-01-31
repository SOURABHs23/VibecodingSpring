package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application Class
 * 
 * @SpringBootApplication is a convenience annotation that combines:
 * - @Configuration: Marks this as a configuration class
 * - @EnableAutoConfiguration: Enables Spring Boot's auto-configuration
 * - @ComponentScan: Scans for components in this package and sub-packages
 */
@SpringBootApplication
public class HelloSpringBootApplication {

    public static void main(String[] args) {
        // This starts the Spring Boot application
        SpringApplication.run(HelloSpringBootApplication.class, args);
        System.out.println("🚀 Spring Boot Application Started Successfully!");
        System.out.println("📍 Visit: http://localhost:8080");
    }
}
