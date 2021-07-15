package com.javi.Libro1.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateEmail {
    public static Boolean validate(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern p = Pattern.compile(regex);
        if (email.isEmpty())
            return false;
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
