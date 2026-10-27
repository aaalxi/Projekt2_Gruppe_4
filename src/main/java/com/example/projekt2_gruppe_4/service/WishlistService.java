package com.example.projekt2_gruppe_4.service;

import com.example.projekt2_gruppe_4.model.Wish;
import com.example.projekt2_gruppe_4.model.Wishlist;
import com.example.projekt2_gruppe_4.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
    public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public void createWishlist(Wishlist wishlist){
        wishlistRepository.create(wishlist);
    }

    public void deleteWishlist(int wishlistId){
        wishlistRepository.deleteById(wishlistId);
    }

    public Wishlist getWishlistById(int wishlistId){
        return wishlistRepository.findById(wishlistId);
    }

    public List<Wishlist> getWishlistsByUserId(int userId){
        return wishlistRepository.findByUserId(userId);
    }

    public Wishlist getWishlistByUserIdAndByTitle(int userId, String title){
        ArrayList<Wishlist> wishlists = new ArrayList<>(wishlistRepository.findByUserId(userId));

        for (Wishlist wishlist : wishlists){
            if (wishlist.getTitle().equals(title)){
                return wishlist;
            }
        }
        return null;
    }
}