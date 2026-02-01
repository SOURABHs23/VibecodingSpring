package com.learning.service;

import com.learning.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public Optional<Task> getTaskById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    public Task createTask(Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return task;
    }

    public Optional<Task> updateTask(Long id, Task updatedTask) {
        return getTaskById(id).map(existingTask -> {
            updatedTask.setId(id);
            tasks.set(tasks.indexOf(existingTask), updatedTask);
            return updatedTask;
        });
    }

    public boolean deleteTask(Long id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }

    public Optional<Task> toggleTaskCompletion(Long id) {
        Optional<Task> taskOpt = getTaskById(id);
        taskOpt.ifPresent(task -> task.setCompleted(!task.isCompleted()));
        return taskOpt;
    }
}
