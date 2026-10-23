package com.example.projekt2_gruppe_4.service;

import com.example.projekt2_gruppe_4.model.Wishlist;
import com.example.projekt2_gruppe_4.repository.WishlistRepository;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public void createWishlist(Wishlist wishlist){
        wishlistRepository.create(wishlist);
    }

}