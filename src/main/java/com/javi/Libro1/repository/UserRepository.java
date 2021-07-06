package com.javi.Libro1.repository;

import com.javi.Libro1.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findUserByEmailAndPassword(String email, String password);
}
