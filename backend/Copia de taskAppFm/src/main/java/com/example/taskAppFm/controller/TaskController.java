package com.example.taskAppFm.controller;

import com.example.taskAppFm.Exceptions.BadRequestException;
import com.example.taskAppFm.Exceptions.ResourceNotFoundException;
import com.example.taskAppFm.domain.Task;
import com.example.taskAppFm.domain.TaskState;
import com.example.taskAppFm.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{id}")
    public Task getTaskById(@PathVariable Long id) throws ResourceNotFoundException {
        return taskService.findTaskByID(id);
    }

    @GetMapping("/list")
    public List<Task> listTasks(){
        return taskService.listTasks();
    }

    @PostMapping("/new")
    public Task newTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) throws ResourceNotFoundException {
        taskService.deleteTaskById(id);
    }

    @PutMapping("/setResponsible")
    public Task setResponsible(@RequestBody Long taskId,String userName) throws ResourceNotFoundException, BadRequestException {
        return taskService.setResponsible(taskId, userName);
    }


}
