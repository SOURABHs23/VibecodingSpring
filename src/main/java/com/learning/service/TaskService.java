package com.learning.service;

import com.learning.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Class
 * 
 * @Service marks this as a Spring-managed service component.
 *          Services contain business logic and act as a bridge between
 *          Controllers and Data (Repository/Database).
 * 
 *          For simplicity, we're using an in-memory List instead of a database.
 */
@Service
public class TaskService {

    // In-memory storage (in real apps, you'd use a database)
    private final List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;

    // Initialize with some sample data
    public TaskService() {
        tasks.add(new Task(nextId++, "Learn Spring Boot", "Understand the basics of Spring Boot", false));
        tasks.add(new Task(nextId++, "Build REST API", "Create endpoints for CRUD operations", false));
        tasks.add(new Task(nextId++, "Practice coding", "Code every day to improve skills", true));
    }

    /**
     * Get all tasks
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Get a task by ID
     */
    public Optional<Task> getTaskById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    /**
     * Create a new task
     */
    public Task createTask(Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return task;
    }

    /**
     * Update an existing task
     */
    public Optional<Task> updateTask(Long id, Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                updatedTask.setId(id);
                tasks.set(i, updatedTask);
                return Optional.of(updatedTask);
            }
        }
        return Optional.empty();
    }

    /**
     * Delete a task
     */
    public boolean deleteTask(Long id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }

    /**
     * Toggle task completion status
     */
    public Optional<Task> toggleTaskCompletion(Long id) {
        Optional<Task> taskOpt = getTaskById(id);
        taskOpt.ifPresent(task -> task.setCompleted(!task.isCompleted()));
        return taskOpt;
    }
}
