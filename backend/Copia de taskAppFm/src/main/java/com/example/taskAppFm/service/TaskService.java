package com.example.taskAppFm.service;

import com.example.taskAppFm.Exceptions.BadRequestException;
import com.example.taskAppFm.Exceptions.ResourceNotFoundException;
import com.example.taskAppFm.domain.Task;
import com.example.taskAppFm.domain.TaskState;
import com.example.taskAppFm.domain.User;
import com.example.taskAppFm.repository.TaskRepository;
import com.example.taskAppFm.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task createTask(Task task){
        //task.setTaskState(TaskState.PENDING);
        return taskRepository.save(task);
    }

    public Task setResponsible(Long id, String userName) throws ResourceNotFoundException, BadRequestException {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()){
            Optional<User> user = userRepository.findByName(userName);
            if (user.isPresent()){
                task.get().setUser(user.get());
                return task.get();
            }
            else {
                throw new BadRequestException("THE USER DOESN'T EXIST");
            }
        }
        else {
            throw new ResourceNotFoundException("THE TASK DOESN'T EXIST");
        }
    }

    public Optional<Task> modifyDate(Long id, LocalDateTime newDateTime) throws  ResourceNotFoundException{
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()){
            try {
                task.get().setDateTime(newDateTime);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            throw new ResourceNotFoundException("THE TASK DOESN'T EXIST");
        }
        return task;
    }

    public void deleteTask(Task task){
        taskRepository.delete(task);
    }

    public void deleteTaskById(Long id) throws ResourceNotFoundException{
        Optional<Task> taskSearch = taskRepository.findById(id);
            if (taskSearch.isPresent()){
                taskRepository.delete(taskSearch.get());
            }
            else {
                throw new ResourceNotFoundException("TASK NOT FOUND");
            }
        }


    public void taskDone(Long id, String nombre){
        Optional<User> userSearch = userRepository.findByName(nombre);
        Optional<Task> taskSearch = taskRepository.findById(id);
        if (userSearch.isPresent() && taskSearch.isPresent()){
            Integer valor = taskSearch.get().getPoints();
            taskSearch.get().setTaskState(TaskState.DONE);
            userSearch.get().setScore(userSearch.get().getScore() + valor);
        }
    }

    public Task findTaskByID(Long id) throws ResourceNotFoundException{
        Optional<Task> taskSearch = taskRepository.findById(id);
        if (taskSearch.isPresent()){
            return taskSearch.get();
        }
        else {
            throw new ResourceNotFoundException("TASK NOT FOUND");
        }
    }

    public List<Task> listTasks(){
        return taskRepository.findAll();
    }

    /*public List<List<Task>> listTasksByUserName(){

    } */

}
