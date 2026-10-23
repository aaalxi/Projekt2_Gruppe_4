package com.example.projekt2_gruppe_4.controller;

import com.example.projekt2_gruppe_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserService service;



}
