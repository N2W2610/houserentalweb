/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trotot.service;

import com.trotot.dto.request.UserCreationRequest;
import com.trotot.dto.request.UserUpdateRequest;
import com.trotot.model.User;
import com.trotot.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tttru
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(UserCreationRequest request){
        User user = new User();

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email existed.");
        }

        user.setFull_name(request.getFull_name());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setRole_id(request.getRole_id());
        user.setStatus(request.getStatus());
        
        return userRepository.save(user);
    }
    public User updateUser(int id,UserUpdateRequest request)
    {
        User user = getUser(id);
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email existed.");
        }

        user.setFull_name(request.getFull_name());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setRole_id(request.getRole_id());
        user.setStatus(request.getStatus());

        return userRepository.save(user);
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public User getUser(int id){
        return userRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new RuntimeException("user not found"));
    }
    public void deleteUser(int userId)
    {
        userRepository.deleteById(String.valueOf(userId));
    }
}
