package com.javi.Libro1.services;

import com.javi.Libro1.domain.User;
import com.javi.Libro1.repository.TokenRepository;
import com.javi.Libro1.utils.ConfirmationToken;
import com.javi.Libro1.utils.InvalidUserException;
import com.javi.Libro1.Dtos.UserDto;
import com.javi.Libro1.utils.ValidateEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;

    public String register(UserDto newUser) {
        Boolean isValidEmail = ValidateEmail.validate(newUser.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }
        String token = userService.createUser(new User(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(),
                 newUser.getPassword()));

        String link = "http://localhost:8080/api/register/confirm?token=" + token;
        emailService.send(
                newUser.getEmail(),
                newUser.getFirstName(),
                link);

        return token;
    }

    public String confirmUserToken(String token){
        ConfirmationToken confirmationToken = tokenRepository
                .findByToken(token)
                .orElseThrow(() -> new InvalidUserException("Token expired or not found"));
        userService.enableUser(confirmationToken.getUser().getEmail());

        return "User enabled succesfully";

    }
}
