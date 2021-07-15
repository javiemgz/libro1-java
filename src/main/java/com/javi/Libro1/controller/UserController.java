package com.javi.Libro1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javi.Libro1.domain.User;
import com.javi.Libro1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    public String post(User user) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(userService.login(user.getEmail(), user.getPassword()));
        }catch(Exception e){
            return e.getMessage();
        }
    }
}
