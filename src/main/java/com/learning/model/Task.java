package com.learning.model;

/**
 * Model Class (also called Entity or POJO)
 * 
 * This represents a Task in our To-Do application.
 * Models hold the data structure of your application.
 */
public class Task {

    private Long id;
    private String title;
    private String description;
    private boolean completed;

    // Default constructor (needed for JSON deserialization)
    public Task() {
    }

    // Constructor with all fields
    public Task(Long id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    // Getters and Setters
    // These are essential for Spring to convert JSON to Java objects and vice versa

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}
