package com.example.projekt2_gruppe_4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncorder(){
        return new BCryptPasswordEncoder();
    }

//verifying password for testData
    public static void main(String []args){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("1234"));
        System.out.println(encoder.matches("1234",
                "$2a$10$Va0NK5SkBpba2bhNzJSs9e6C/2sX0o/4NwZx.XVno3ThLdmx8H7hy"));
    }
}