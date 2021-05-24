package com.javi.Libro1.utils;

import java.util.ArrayList;

public class InvalidUserException extends Exception {
    public InvalidUserException(ArrayList<String> errors) {
        super("The attribute/s" + errors + "are missing");
    }
}
