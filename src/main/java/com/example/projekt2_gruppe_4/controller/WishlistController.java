package com.example.projekt2_gruppe_4.controller;

import com.example.projekt2_gruppe_4.model.Wish;
import com.example.projekt2_gruppe_4.model.Wishlist;
import com.example.projekt2_gruppe_4.service.WishService;
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
    private final WishService wishService;

    public WishlistController(WishlistService wishlistService, WishService wishService) {
        this.wishlistService = wishlistService;
        this.wishService = wishService;
    }

    @GetMapping("/wishlist/{wishlistId}/showWishlist")
    public String showWishlist(Model model, @PathVariable int wishlistId){
        Wishlist wishlist = wishlistService.getWishlistById(wishlistId);
        model.addAttribute("wishlist", wishlistService.getWishlistById(wishlistId));
        model.addAttribute("wishes", wishService.getWishesByWishlistId(wishlistId));
        model.addAttribute("userId", wishlist.getUserId());
        return "wishlist";
    }

    @GetMapping("/wishlist/{userId}/showCreateWishlist")
    public String showCreateWishlist(Model model, @PathVariable int userId){
        model.addAttribute("userId", userId);
        return "createWishlist";
    }

    @PostMapping("/wishlist/createWishlist")
    public String createWishlist(@ModelAttribute Wishlist wishlist){
        //Gemmer wishlist i databasen
        wishlistService.createWishlist(wishlist);
        //Henter wishlist fra databasen, med wishlistId.
        Wishlist wishlistWithId = wishlistService.getWishlistByUserIdAndByTitle(wishlist.getUserId(), wishlist.getTitle());
        return "redirect:/wishlist/" + wishlistWithId.getWishlistId() + "/showWishlist";
    }

    @PostMapping("/wishlist/deleteWishlist")
    public String deleteWishlist(@ModelAttribute int wishlistId){
        wishlistService.deleteWishlist(wishlistId);
        return "redirect:/profile";
    }


}
