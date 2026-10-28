package com.example.projekt2_gruppe_4.controller;

import com.example.projekt2_gruppe_4.model.User;
import com.example.projekt2_gruppe_4.repository.SharedWishlistRepository;
import com.example.projekt2_gruppe_4.service.SharedWishlistService;
import com.example.projekt2_gruppe_4.service.UserService;
import com.example.projekt2_gruppe_4.service.WishlistService;
import jakarta.servlet.http.HttpSession;
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

    //WHEN CLICK ON "SHARE" BTN ON WISHLIST.HTML, the wishlistId is sent to th:template
    @GetMapping("/wishlist{wishlistId}/share")
    public String shareWishlist(@PathVariable int wishlistId,
                                HttpSession session,
                                Model model){
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId==null){
            return "redirect:/";        //redirects if sessionAttb. is null
        }
        if (wishlistService.getWishlistById(wishlistId).getUserId()!= userId){
            return "redirect:/profile";  //redirects if userId not the same as session
        }
        model.addAttribute("wishlistId", wishlistId);
        return "shareWishlist";
    }

    @PostMapping("/wishlist/share")
    public String shareWishlist(
            @RequestParam int wishlistId,
            @RequestParam String email,
            HttpSession session,
            RedirectAttributes redirectAttributes){
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId==null){
            return "redirect:/";
        }
        //find the wishlist
        User sharedWithUser = userService.getByEmail()
    }
    )

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