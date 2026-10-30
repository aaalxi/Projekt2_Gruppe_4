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
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {

    private final UserService service;
    private final WishlistService wishlistService;

    public UserController(UserService service, WishlistService wishlistService) {
        this.service = service;
        this.wishlistService = wishlistService; //er til at få wishlist på den enkelte profil.
    }

    @PostMapping("/postCreateUser")
    public String saveUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        service.addUser(new User(name, email, password));
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        User user = service.login(email, password);

        if (user == null) {
            model.addAttribute("error", "Forkert email eller password");
            return "redirect:/";
        }
        session.setAttribute("userId", user.getUserId());
        return "redirect:/profile";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    //SECURITY FOR PROFILE.HTML: Control of session
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }
        User user = service.getById(userId);
        if (user == null) {
            session.invalidate();
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        model.addAttribute("wishlists", wishlistService.getWishlistsByUserId(userId));
        return "profile";
    }

    @PostMapping("/profile/editNameAndEmail")
    public String editNameAndEmail(@ModelAttribute User user, HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        user.setUserId(userId);
        try {
            service.editNameAndEmail(user);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("profile", user);
            return "redirect:/profile";
        }
        return "redirect:/profile";
    }


    @GetMapping("/confirmDeleteUser")
    public String getDeleteUser() {
        return "confirmDeleteUser";
    }

    @PostMapping("/deleteUser")
    public String postDeleteUser(
            @RequestParam String password,
            HttpSession session,
            Model model) {

        Integer userId = (Integer) session.getAttribute("userId");  //parsing value to Integer

        System.out.println("UserId fra session: " + userId);
        if (userId == null) {
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
//    @GetMapping("/profile/showEditNameAndEmail")
//    public String showEditNameAndEmail(HttpSession session, Model model){
//        Integer userId = (Integer) session.getAttribute("userId");
//        if (userId == null){
//            return "redirect:/login";
//        }
//        model.addAttribute("profile", service.getById(userId));
//        return "editProfile";
//    }

//    @GetMapping("/ogin")
//    public String logIn(){
//        return "login";
//    }
//
//   @GetMapping("/getCreateUser")
//   public String getCreateUser(){
//       return "createUser";
//   }

}