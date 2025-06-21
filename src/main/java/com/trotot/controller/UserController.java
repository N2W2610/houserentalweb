/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trotot.controller;

import com.trotot.dto.request.ApiResponse;
import com.trotot.dto.request.UserCreationRequest;
import com.trotot.dto.request.UserUpdateRequest;
import com.trotot.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.trotot.service.UserService;


import java.util.List;

/**
 *
 * @author tttru
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
       ApiResponse<User> apiResponse = new ApiResponse<>();
       apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    List<User> getUser(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") int userId){
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable("userId") int userID, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userID, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}
