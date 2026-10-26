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

    @GetMapping("/wish/{wishlistId}/showCreateWish")
    public String showCreateWish(Model model, @PathVariable int wishlistId){
        model.addAttribute("wishlistId", wishlistId);
        model.addAttribute("wish", new Wish(wishlistId));
        return "createWish";
    }

    @PostMapping("/wish/createWish")
    public String createWish(@ModelAttribute Wish wish){
        wishService.createWish(wish);
        return "redirect:/" + wish.getWishlistId() +"/wishlist";
    }

    @GetMapping("/wish/{wishId}/showEditWish")
    public String showEditWish(@PathVariable int wishId, Model model){
        model.addAttribute("wish", wishService.getById(wishId));
        return "editWish";
    }

    @PostMapping("/wish/editWish")
    public String editWish(@ModelAttribute Wish wish){
        wishService.editWish(wish);
        return "redirect:/wishlist";
    }

//    @GetMapping("/wish/{wishId}/showDeleteWish")
//    public String showDeleteWish(@PathVariable int wishId, Model model){
//        model.addAttribute("wish", wishService.getById(wishId));
//        return "deleteWish";
//    }

    @PostMapping("/wish/{wishId}/deleteWish")
    public String deleteWish(@PathVariable int wishId){
        wishService.deleteWish(wishId);
        return "redirect:/wishlist";
    }
}