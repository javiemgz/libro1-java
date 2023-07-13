package com.javi.Libro1.controller;

import com.javi.Libro1.Dtos.UserDto;
import com.javi.Libro1.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService regService;

    @PostMapping
    public String register(@RequestBody UserDto request) {
        return regService.register(request);

    }
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return regService.confirmUserToken(token);
    }

}
