package com.example.projekt2_gruppe_4.controller;

import com.example.projekt2_gruppe_4.model.Wish;
import com.example.projekt2_gruppe_4.model.Wishlist;
import com.example.projekt2_gruppe_4.service.SharedWishlistService;
import com.example.projekt2_gruppe_4.service.WishService;
import com.example.projekt2_gruppe_4.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WishController {

    private final WishService wishService;
    private final SharedWishlistService sharedWishlistService;

    public WishController(WishService wishService, SharedWishlistService sharedWishlistService) {
        this.wishService = wishService;
        this.sharedWishlistService = sharedWishlistService;
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

    @PostMapping("/sharedWishlists/{wishId}/reserve")
    public String reserveWish (@PathVariable int wishId,
                               HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null){
            return "redirect:/";
        }

        Wish wish = wishService.getById(wishId);
        if (!sharedWishlistService.userHasAccessToWishlist(wish.getWishlistId(), userId)){
            return "redirect:/sharedWishlists";
        }

        wishService.reserveWish(wishId, userId);
        return "redirect:/sharedWishlists";
    }


    @PostMapping ("sharedWishlists/{wishId}/cancelReservation")
    public String cancelReservation (@PathVariable int wishId,
                                     HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null){
            return "redirect:/";
        }
        //url protection,
        Wish wish = wishService.getById(wishId);
        if (!sharedWishlistService.userHasAccessToWishlist(wish.getWishlistId(), userId)){
            return "redirect:/sharedWishlists";
        }
        boolean cancelled = wishService.cancelReservation(wishId, userId);
        System.out.println("Wish ID: " + wishId);
        System.out.println("User ID: " + userId);
        System.out.println("Reservation cancelled: " + cancelled);

        wishService.cancelReservation(wishId, userId);
        return "redirect:/sharedWishlists";
    }

}