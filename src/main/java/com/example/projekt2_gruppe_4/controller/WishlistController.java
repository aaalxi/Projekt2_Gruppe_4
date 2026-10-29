package com.example.projekt2_gruppe_4.controller;

import com.example.projekt2_gruppe_4.model.Wish;
import com.example.projekt2_gruppe_4.model.Wishlist;
import com.example.projekt2_gruppe_4.service.WishService;
import com.example.projekt2_gruppe_4.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("wish", new Wish(wishlistId));
        return "wishlist";
    }

    @GetMapping("/wishlist/{wishlistId}/showWishlistSortedByPrice")
    public String showWishlistSortedByPrice(Model model, @PathVariable int wishlistId){
        Wishlist wishlist = wishlistService.getWishlistById(wishlistId);
        model.addAttribute("wishlist", wishlistService.getWishlistById(wishlistId));
        model.addAttribute("wishes", wishService.getWishesSortedByPrice(wishlistId));
        model.addAttribute("userId", wishlist.getUserId());
        return "wishlist";
    }

    @GetMapping("/wishlist/{wishlistId}/showWishlistSortedByPriority")
    public String showWishlistSortedByPriority(Model model, @PathVariable int wishlistId){
        Wishlist wishlist = wishlistService.getWishlistById(wishlistId);
        model.addAttribute("wishlist", wishlistService.getWishlistById(wishlistId));
        model.addAttribute("wishes", wishService.getWishesSortedByPriority(wishlistId));
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
    public String deleteWishlist(@RequestParam int wishlistId){
        wishlistService.deleteWishlist(wishlistId);
        return "redirect:/profile";
    }
}
