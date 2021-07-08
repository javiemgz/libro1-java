package com.javi.Libro1.utils;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;

@ResponseStatus()
public class InvalidUserException extends Exception {
    public InvalidUserException(ArrayList<String> errors) {
        super("The attribute/s" + errors + "are missing");
    }
}
