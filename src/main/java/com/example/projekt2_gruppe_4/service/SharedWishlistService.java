package com.example.projekt2_gruppe_4.service;

import com.example.projekt2_gruppe_4.model.SharedWishlist;
import com.example.projekt2_gruppe_4.repository.SharedWishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SharedWishlistService {

    private final SharedWishlistRepository repository;

    public SharedWishlistService(SharedWishlistRepository repository){
        this.repository=repository;
    }

    public void shareWishlist(int wishlistId, int userId){
        repository.shareWishlist(wishlistId, userId);
    }

    public List<SharedWishlist> getSharedWishlistsByUserId(int userId){
        return repository.findSharedWishlistsByUserId(userId);
    }

}