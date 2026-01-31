package com.learning.controller;

import com.learning.model.Task;
import com.learning.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Task REST Controller
 * 
 * This demonstrates a full CRUD (Create, Read, Update, Delete) REST API.
 * 
 * @RestController - Marks this as a REST controller
 *                 @RequestMapping("/api/tasks") - Base URL for all endpoints in
 *                 this controller
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    // Dependency Injection - Spring automatically provides the TaskService instance
    private final TaskService taskService;

    // Constructor Injection (recommended way)
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * GET all tasks
     * HTTP GET /api/tasks
     * 
     * Try: http://localhost:8080/api/tasks
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    /**
     * GET a single task by ID
     * HTTP GET /api/tasks/{id}
     * 
     * Try: http://localhost:8080/api/tasks/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * CREATE a new task
     * HTTP POST /api/tasks
     * 
     * @RequestBody tells Spring to convert the JSON body to a Task object
     * 
     *              Test with curl:
     *              curl -X POST http://localhost:8080/api/tasks \
     *              -H "Content-Type: application/json" \
     *              -d '{"title":"New Task","description":"Task
     *              description","completed":false}'
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    /**
     * UPDATE an existing task
     * HTTP PUT /api/tasks/{id}
     * 
     * Test with curl:
     * curl -X PUT http://localhost:8080/api/tasks/1 \
     * -H "Content-Type: application/json" \
     * -d '{"title":"Updated Task","description":"Updated
     * description","completed":true}'
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE a task
     * HTTP DELETE /api/tasks/{id}
     * 
     * Test with curl:
     * curl -X DELETE http://localhost:8080/api/tasks/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskService.deleteTask(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * TOGGLE task completion
     * HTTP PATCH /api/tasks/{id}/toggle
     * 
     * PATCH is used for partial updates
     * 
     * Test with curl:
     * curl -X PATCH http://localhost:8080/api/tasks/1/toggle
     */
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Task> toggleTaskCompletion(@PathVariable Long id) {
        return taskService.toggleTaskCompletion(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
