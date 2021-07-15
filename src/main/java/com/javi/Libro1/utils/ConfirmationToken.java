package com.javi.Libro1.utils;

import com.javi.Libro1.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ConfirmationToken {
    String token;
    User user;
}
