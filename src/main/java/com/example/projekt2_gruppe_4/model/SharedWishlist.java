package com.example.projekt2_gruppe_4.model;

public class SharedWishlist {
    private int shareId;
    private int wishlistId;
    private int sharedWithUserId;
    private String title;
    private String owner;

    public SharedWishlist(int shareId, int wishlistId, int sharedWithUserId,
                          String title, String ownerName) {
        this.shareId = shareId;
        this.wishlistId = wishlistId;
        this.sharedWithUserId = sharedWithUserId;
        this.title = title;
        this.owner = ownerName;
    }

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getSharedWithUserId() {
        return sharedWithUserId;
    }

    public void setSharedWithUserId(int sharedWithUserId) {
        this.sharedWithUserId = sharedWithUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}