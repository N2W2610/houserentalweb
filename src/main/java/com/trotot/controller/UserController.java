package com.trotot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.trotot.dto.UserDTO;
import com.trotot.repository.UserRepository;


@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String LoginUserPage(Model model)
    {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("cur_user",userDTO);
        return "login";
    }
   
}
