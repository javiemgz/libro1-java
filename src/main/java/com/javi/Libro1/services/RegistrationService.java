package com.javi.Libro1.services;

import com.javi.Libro1.domain.User;
import com.javi.Libro1.utils.InvalidUserException;
import com.javi.Libro1.utils.UserDto;
import com.javi.Libro1.utils.ValidateEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;

    public String register (UserDto newUser){
        Boolean isValidEmail = ValidateEmail.validate(newUser.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("Email not valid");
        }
        userService.createUser(new User(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getPassword()));
        return "Working";
    }
}
