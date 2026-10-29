package com.example.projekt2_gruppe_4.controller;

import com.example.projekt2_gruppe_4.model.User;
import com.example.projekt2_gruppe_4.model.Wishlist;
import com.example.projekt2_gruppe_4.service.SharedWishlistService;
import com.example.projekt2_gruppe_4.service.UserService;
import com.example.projekt2_gruppe_4.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SharedWishlistController {

    private final SharedWishlistService sharedWishlistService;
    private final UserService userService;
    private final WishlistService wishlistService;

    public SharedWishlistController(SharedWishlistService sharedWishlistService,
                                    UserService userService,
                                    WishlistService wishlistService){
        this.sharedWishlistService=sharedWishlistService;
        this.userService=userService;
        this.wishlistService=wishlistService;
    }

    @PostMapping("/wishlist/share")
    public String shareWishlist(
            @RequestParam int wishlistId,
            @RequestParam String email,
            HttpSession session,
            RedirectAttributes redirectAttributes){

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null){
            return "redirect:/";
        }

        Wishlist wishlist = wishlistService.getWishlistById(wishlistId);

        if (wishlist.getUserId() != userId){
            return "redirect:/";
        }

        User sharedWithUser = userService.getByEmail(email);
        if (sharedWithUser == null){
            redirectAttributes.addFlashAttribute("shareMessage",
                    "There's no active Users with this email.");
            return "redirect:/wishlist/"+wishlistId+"/showWishlist";
        }

        if (sharedWithUser.getUserId() == userId){
            redirectAttributes.addFlashAttribute("shareMessage",
                    "You can't share it with yourself");
            return "redirect:/wishlist/"+wishlistId+"/showWishlist";
        }

        try{
            sharedWishlistService.shareWishlist(wishlistId, sharedWithUser.getUserId());
            redirectAttributes.addFlashAttribute("shareMessage",
                    "Wishlist has been shared!");
        } catch (DuplicateKeyException e) {
            redirectAttributes.addFlashAttribute("shareMessage",
                    "This wishlist has already been shared with the user");
        }
        return "redirect:/wishlist/"+wishlistId+"/showWishlist";
    }

//this one is not made yet
    @GetMapping("/sharedWishlists")
    public String showSharedWishlist(HttpSession session, Model model){
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId==null){
            return "redirect:/login";
        }
        model.addAttribute("sharedWishlists",
                sharedWishlistService.getSharedWishlistsByUserId(userId));

        return "sharedWishlists";
    }
}