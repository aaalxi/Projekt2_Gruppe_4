package com.example.projekt2_gruppe_4.controller;

import com.example.projekt2_gruppe_4.model.Wish;
import com.example.projekt2_gruppe_4.service.WishService;
import com.example.projekt2_gruppe_4.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WishController {

    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("wish/showCreateWish")
    public String showCreateWish(Model model){
        model.addAttribute("wish", new Wish());
        return "createWish";
    }

    @PostMapping("/wish/createWish")
    public String createWish(@ModelAttribute Wish wish){
        wishService.createWish(wish);
        return "redirect:/profile";
    }

}