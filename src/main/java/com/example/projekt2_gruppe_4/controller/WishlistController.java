package com.example.projekt2_gruppe_4.controller;

import com.example.projekt2_gruppe_4.model.Wish;
import com.example.projekt2_gruppe_4.model.Wishlist;
import com.example.projekt2_gruppe_4.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WishlistController {
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/{wishlistId}/wishlist")
    public String showWishlist(Model model, @PathVariable int wishlistId){
        model.addAttribute("wishlist", wishlistService.getWishlistById(wishlistId));
        return "wishlist";
    }

    @GetMapping("/wishlist/{userId}/showCreateWishlist")
    public String showCreateWishlist(Model model, @PathVariable int userId){
        model.addAttribute("userId", userId);
        return "createWishlist";
    }

    @PostMapping("/wishlist/createWishlist")
    public String createWishlist(@ModelAttribute Wishlist wishlist){
        wishlistService.createWishlist(wishlist);
        return "redirect:/wishlist";
    }

    @PostMapping("/wishlist/deleteWishlist")
    public String deleteWishlist(@ModelAttribute int wishlistId){
        wishlistService.deleteWishlist(wishlistId);
        return "redirect:/profile";
    }


}
