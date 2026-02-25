package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // ← Required for Cron Jobs / Scheduler to work!
public class HelloSpringBootApplication {

    public static void main(String[] args) {
        // This starts the Spring Boot application
        SpringApplication.run(HelloSpringBootApplication.class, args);
        System.out.println("🚀 Spring Boot Application Started Successfully!");
        System.out.println("📍 Visit: http://localhost:8080");
    }
}
