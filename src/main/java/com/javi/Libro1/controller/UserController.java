package com.javi.Libro1.controller;

import com.javi.Libro1.domain.User;
import com.javi.Libro1.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/register")
    @ApiOperation(value = "Create new user", notes = "Creates new user in the platform")
    public String register(User user) {
        try{
            userService.createUser(user);
            return "ok";
        }catch(Exception e){
            return "No es por ahi bro";
        }
    }

    @GetMapping(value = "/login")
    public String post() {
        return "pong";
    }
}
