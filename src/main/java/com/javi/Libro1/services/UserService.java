package com.javi.Libro1.services;

import com.javi.Libro1.domain.User;
import com.javi.Libro1.repository.UserRepository;
import com.javi.Libro1.utils.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository urepo;

    private final static String USER_NOT_FOUND = "User wth email %s not found";

    public void createUser(User user) throws InvalidUserException {
        user.validate();
        urepo.save(user);
    }

    public User login(String email, String password) throws Exception {
        List<User> u = urepo.findUserByUsernameAndPassword(email, password);
        if (u.size() != 1)
            throw new Exception("Wrong email and password combination");
        return u.get(0);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return urepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, username)));
    }
}
