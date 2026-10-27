package com.example.projekt2_gruppe_4.controller;

import com.example.projekt2_gruppe_4.model.User;
import com.example.projekt2_gruppe_4.service.UserService;
import com.example.projekt2_gruppe_4.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

   private final UserService service;
   private final WishlistService wishlistService;

   public UserController (UserService service, WishlistService wishlistService){
       this.service=service;
       this.wishlistService = wishlistService; //er til at få wishlist på den enkelte profil.
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
     return "redirect:/login";
   }

    @GetMapping("/login")
    public String logIn(){
        return "login";
    }

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
        }
        session.setAttribute("userId", user.getUserId());
        return "redirect:/profile";
    }

    @PostMapping("/logout")
    public String logout (HttpSession session){
       session.invalidate();
       return "redirect:/";
    }

    //SECURITY FOR PROFILE.HTML: Control of session
    @GetMapping ("/profile")
    public String showProfile (HttpSession session, Model model){

       Integer userId = (Integer) session.getAttribute("userId");

       if (userId == null){
           return "redirect:/login";
       }
       User user = service.getById(userId);
       if (user == null){
           session.invalidate();
           return "redirect:/login";
       }
       model.addAttribute("user", user);
       model.addAttribute("wishlists", wishlistService.getWishlistsByUserId(userId));
       return "profile";
    }


    @GetMapping ("/confirmDeleteUser")
    public String getDeleteUser(){
       return "confirmDeleteUser";
    }

    @PostMapping("/deleteUser")
    public String postDeleteUser (
            @RequestParam String password,
            HttpSession session,
            Model model){

       Integer userId = (Integer) session.getAttribute("userId");  //parsing value to Integer

        System.out.println("UserId fra session: " + userId);
        if(userId==null){
            return "redirect:/getLogin";
        }

        System.out.println("Forsøger slette bruger" + userId);
        boolean deleted = service.deleteUser(userId, password);
        if (!deleted) {
            model.addAttribute("error", "Incorrect password");
            return "confirmDeleteUser";
        }
        session.invalidate();
        return "redirect:/";
    }

}
