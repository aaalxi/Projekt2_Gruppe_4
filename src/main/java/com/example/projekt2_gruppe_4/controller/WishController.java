package com.example.projekt2_gruppe_4.controller;

import com.example.projekt2_gruppe_4.model.Wish;
import com.example.projekt2_gruppe_4.model.Wishlist;
import com.example.projekt2_gruppe_4.service.WishService;
import com.example.projekt2_gruppe_4.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WishController {

    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @PostMapping("/wish/createWish")
    public String createWish(@ModelAttribute Wish wish){
        wishService.createWish(wish);
        return "redirect:/wishlist/" + wish.getWishlistId() +"/showWishlist";
    }

    @PostMapping("/wish/editWish")
    public String editWish(@ModelAttribute Wish wish){
        wishService.editWish(wish);
        return "redirect:/wishlist/" + wish.getWishlistId() + "/showWishlist";
    }

    @PostMapping("/wish/deleteWish")
    public String deleteWish(@RequestParam int wishId, @RequestParam int wishlistId){
        wishService.deleteWish(wishId);
        return "redirect:/wishlist/" + wishlistId + "/showWishlist";
    }

    //    @GetMapping("/wish/{wishId}/showEditWish")
//    public String showEditWish(@PathVariable int wishId, Model model){
//        model.addAttribute("wish", wishService.getById(wishId));
//        return "editWish";
//    }

//    @GetMapping("/wish/{wishlistId}/showCreateWish")
//    public String showCreateWish(Model model, @PathVariable int wishlistId){
//        Wishlist wishlist = wishlistService.getWishlistById(wishlistId);
//        model.addAttribute("wishlistId", wishlistId);
//        model.addAttribute("userId", wishlist.getUserId());
//        model.addAttribute("wish", new Wish(wishlistId));
//        return "createWish";
//    }
//    @GetMapping("/wish/{wishId}/showDeleteWish")
//    public String showDeleteWish(@PathVariable int wishId, Model model){
//        model.addAttribute("wish", wishService.getById(wishId));
//        return "deleteWish";
//    }


}