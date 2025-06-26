package com.trotot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.trotot.dto.UserDTO;
import com.trotot.model.User;
import com.trotot.repository.RoleRepository;
import com.trotot.repository.UserRepository;

import jakarta.validation.Valid;


@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/dashboard")
    public String AdminPage(Model model){
        return "dashboard";
    }

    @GetMapping("/login")
    public String LoginUserPage(Model model)
    {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("cur_user",userDTO);
        return "login";
    }

    @GetMapping("/register")
    public String RegisterUserPage(Model model)
    {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("cur_user",userDTO);
        return "register";
    }
    @PostMapping("/register")
    public String RegisterUser(
        @Valid @ModelAttribute("cur_user") UserDTO userDTO,
            BindingResult result
    ){
        if (result.hasErrors()) {
            return "register";
        }
        
        // Check if email already exists
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            result.rejectValue("email", "error.user", "Email already exists");
            return "register";
        }
        
         User user = new User();
    user.setEmail(userDTO.getEmail());
    user.setPassword(userDTO.getPassword()); // For production, encode the password!
    user.setFull_name(userDTO.getFull_name());
    user.setPhone(userDTO.getPhone());
    user.setRole(roleRepository.findByName("guest").getId());
    user.setStatus(0);

        // Save the user    
        userRepository.save(user);
        
        return "redirect:/login";
    }
   
}
