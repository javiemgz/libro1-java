package com.javi.Libro1.controller;

import com.javi.Libro1.utils.InvalidUserException;
import com.javi.Libro1.utils.UserDto;
import com.javi.Libro1.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService regService;

    @PostMapping
    public String register(@RequestBody UserDto request) {

        return regService.register(request);

    }

}
