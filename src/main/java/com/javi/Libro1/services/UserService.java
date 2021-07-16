package com.javi.Libro1.services;

import com.javi.Libro1.domain.User;
import com.javi.Libro1.repository.TokenRepository;
import com.javi.Libro1.repository.UserRepository;
import com.javi.Libro1.utils.ConfirmationToken;
import com.javi.Libro1.utils.InvalidUserException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    TokenRepository tokenRepo;

    private final static String USER_NOT_FOUND = "User wth email %s not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String createUser(User user){
        boolean userExists =
                userRepository.findByEmail(user.getEmail())
                .isPresent();
        if (userExists)
            throw new InvalidUserException("User already exists");

        user.validate();

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        tokenRepo.save(confirmationToken);
        return confirmationToken.getToken();
    }

    public User login(String email, String password) throws Exception {
        List<User> u = userRepository.findUserByEmailAndPassword(email, password);
        if (u.size() != 1)
            throw new Exception("Wrong email and password combination");
        return u.get(0);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public void enableUser(String email){
        User user = userRepository.findByEmail(email).orElseThrow((() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email))));

        if (user.isEnabled())
            throw new InvalidUserException("User already enabled");

        user.setEnabled(true);
        userRepository.save(user);
    }
}
