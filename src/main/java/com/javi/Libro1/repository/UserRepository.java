package com.javi.Libro1.repository;

import com.javi.Libro1.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
