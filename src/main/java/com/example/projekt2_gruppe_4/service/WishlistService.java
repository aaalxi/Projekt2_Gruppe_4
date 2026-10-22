package com.example.projekt2_gruppe_4.service;

import com.example.projekt2_gruppe_4.model.Wishlist;
import com.example.projekt2_gruppe_4.repository.WishlistRepo;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    private final WishlistRepo wishlistRepo;

    public WishlistService(WishlistRepo wishlistRepo) {
        this.wishlistRepo = wishlistRepo;
    }

    public void createWishlist(Wishlist wishlist){
        wishlistRepo.create(wishlist);
    }

}