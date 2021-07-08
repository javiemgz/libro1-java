package com.javi.Libro1.services;

import com.javi.Libro1.utils.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public String register (RegistrationRequest request){

        return "Working";
    }
}
