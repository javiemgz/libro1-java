package com.javi.Libro1.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    @GetMapping(value="/pig")
    public String ping(){
        return "pong";
    }
    @GetMapping(value="/tests")
    public String post(){
        return "pong";
    }
}
