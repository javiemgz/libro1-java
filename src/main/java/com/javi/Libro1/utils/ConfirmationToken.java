package com.javi.Libro1.utils;

import com.javi.Libro1.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ConfirmationToken {
    String token;
    User user;

    public ConfirmationToken(User user){
        this.user = user;
        token = UUID.randomUUID().toString();
    }
}
