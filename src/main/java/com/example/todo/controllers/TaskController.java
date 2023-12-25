package com.example.todo.controllers;
import com.example.todo.models.Task;
import com.example.todo.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/tasks")
@Tag(name = "ToDo", description = "the ToDo Api")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Operation(
            summary = "Fetch all tasks",
            description = "fetches all plant entities and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTask());
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks() {
        return ResponseEntity.ok(taskService.findAllCompletedTask());
    }
    @GetMapping("/incomplete")
    public ResponseEntity<List<Task>> getAllIncompleteTasks() {
        return ResponseEntity.ok(taskService.findAllInCompleteTask());
    }

    @PostMapping("/")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createNewTask(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> getAllTasks(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return ResponseEntity.ok(taskService.updateTask(task));
    }


}
