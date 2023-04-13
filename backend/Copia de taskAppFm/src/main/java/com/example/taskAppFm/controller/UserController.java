package com.example.taskAppFm.controller;

import com.example.taskAppFm.Exceptions.ResourceNotFoundException;
import com.example.taskAppFm.domain.User;
import com.example.taskAppFm.dto.UserDTO;
import com.example.taskAppFm.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody UserDTO userDTO){
       return userService.createUser(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserId(@PathVariable Long id) throws ResourceNotFoundException {
        userService.deleteUserId(id);
    }

    @GetMapping("/{id}")
    public User getUserId(@PathVariable Long id) throws ResourceNotFoundException {
        return userService.findUserById(id);
    }

}
