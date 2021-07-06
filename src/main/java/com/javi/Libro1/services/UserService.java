package com.javi.Libro1.services;

import com.javi.Libro1.domain.User;
import com.javi.Libro1.repository.UserRepository;
import com.javi.Libro1.utils.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository urepo;

    public void createUser(User user) throws InvalidUserException {
        user.validate();
        urepo.save(user);
    }

    public User login (String email, String password) throws Exception {
        List<User> u = urepo.findUserByEmailAndPassword(email, password);
        if(u.size() != 1)
            throw new Exception("Wrong email and password combination");
        return u.get(0);

    }
}
