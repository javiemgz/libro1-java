package com.javi.Libro1.services;

import com.javi.Libro1.domain.User;
import com.javi.Libro1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository urepo;

    public void createUser(User user){
        urepo.save(user);
    }
}
