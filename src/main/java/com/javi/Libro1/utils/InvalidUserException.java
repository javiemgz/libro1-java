package com.javi.Libro1.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidUserException extends RuntimeException {
    public InvalidUserException(ArrayList<String> errors) {
        super("The attribute/s" + errors + "are missing");
    }
    public InvalidUserException(String error) {
        super(error);
    }
}
