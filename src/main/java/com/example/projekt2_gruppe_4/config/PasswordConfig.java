package com.example.projekt2_gruppe_4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    //Return type: PasswordEncoder, but the object is a BCryptEncoder: Interface (PasswordEncoder) > Implements (Class)
    @Bean
    public PasswordEncoder passwordEncorder(){
        return new BCryptPasswordEncoder();
    }
}


//Config: tells Spring, this class has instructions about config and creating objects
//Bean:
//PasswordEncoder: interface, PasswordEncoder is a BCrypt..., has methods: .encode(), .mathces()
//BCryptEncoder: the 'real' class that hashes the password with BCrypt.