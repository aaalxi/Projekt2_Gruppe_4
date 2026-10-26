package com.example.projekt2_gruppe_4.controller;

import com.example.projekt2_gruppe_4.model.User;
import com.example.projekt2_gruppe_4.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
           @RequestParam("password") String password) {
     service.addUser(new User(name, email, password));
     return "createUser";
   }

    @GetMapping("/login")
    public String logIn(){
        return "login";
    }

    //Ved klik på login: Session:
    @PostMapping ("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model){
        User user = service.login(email, password);

        if(user == null){
            model.addAttribute("error", "Forkert email eller password");
            return "login";
        } //SETTING THE SESSION ATTRIBUTE
        session.setAttribute("loggedInUserId", user.getUserId());
        return "redirect:/profile";
    }

    //LOGOUT: destroying the active user in session with method invalidate
    @PostMapping("/logout")
    public String logout (HttpSession session){
       session.invalidate();
       return "redirect:/login";
    }


    //SECURITY FOR PROFILE.HTML: NO INSERT URL TO ACCESS PROFILE, CONTROL OF SESSION
    @GetMapping ("/profile")
    public String showProfile (HttpSession session, Model model){

       Integer userId = (Integer) session.getAttribute("loggedInUserId");
       //NO INSERTION OF URL, HAS TO LOG IN FIRST, AND THEREBY HAVE A SESSION-ATTRIBUTE
       if (userId == null){
           return "redirect:/login";
       }
       User user = service.getById(userId);
       if (user == null){
           session.invalidate();
           return "redirect:/login";
       }
       model.addAttribute("user", user);
       return "profile";
    }
//IMPORTANT: USE userId from session.getAttribute and NOT the id in url or form

}
