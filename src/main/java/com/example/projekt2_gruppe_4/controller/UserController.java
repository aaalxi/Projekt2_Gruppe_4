package com.example.projekt2_gruppe_4.controller;

import com.example.projekt2_gruppe_4.model.User;
import com.example.projekt2_gruppe_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

   private final UserService service;

   public UserController (UserService service){
       this.service=service;
   }


   @GetMapping("/getCreateUser")
   public String createUser(){
       return "createUser";
   }

    @PostMapping("/postCreateUser")
   public String saveUser(
           @RequestParam("name") String name,
           @RequestParam("email") String email,
           @RequestParam("password") String password_hash) {
     service.addUser(new User(name, email, password_hash));
     return "profile";
   }




}
