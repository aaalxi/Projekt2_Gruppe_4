package com.example.projekt2_gruppe_4.model;

import java.time.LocalDateTime;

public class Wishlist {
    private int wishlistId;
    private int userId;
    private String title;
    private LocalDateTime createdAt;
    private String sharedToken;

    public Wishlist(int wishlistId, int userId, String title, LocalDateTime createdAt){
        this.wishlistId = wishlistId;
        this.userId = userId;
        this.title = title;
        this.createdAt = createdAt;
    }

    public Wishlist(int wishlistId, int userId, String title, LocalDateTime createdAt, String sharedToken){
        this.wishlistId = wishlistId;
        this.userId = userId;
        this.title = title;
        this.createdAt = createdAt;
        this.sharedToken = sharedToken;
    }

    public Wishlist(int wishlistId, int userId, String title){
        this.wishlistId = wishlistId;
        this.userId = userId;
        this.title = title;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setSharedToken(String sharedToken) {
        this.sharedToken = sharedToken;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getSharedToken() {
        return sharedToken;
    }


}
