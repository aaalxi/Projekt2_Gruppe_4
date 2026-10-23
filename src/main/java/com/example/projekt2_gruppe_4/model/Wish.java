package com.example.projekt2_gruppe_4.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Wish {

    private int wishId;
    private int wishListId;
    private String title;
    private String description;
    private String productUrl;
    private BigDecimal price;
    private int amount;
    private String imgUrl;
    private int priority;
    private LocalDateTime createDate;
    private int reservedByUserId;
    //private LocalDateTime reservedDate; ???



    public Wish (int wishId, int wishListId, String title, String description, String productUrl,
                 BigDecimal price, int amount, String imgUrl,
                 int priority, LocalDateTime createDate, int reservedByUserId){
        this.wishListId = wishListId;
        this.title = title;
        this.description = description;
        this.productUrl = productUrl;
        this.price = price;
        this.amount = amount;
        this.imgUrl = imgUrl;
        this.priority = priority;
        this.createDate = createDate;
        this.reservedByUserId = reservedByUserId;

    }

    public Wish(){

    }

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public int getReservedByUserId() {
        return reservedByUserId;
    }

    public void setReservedByUserId(int reservedByUserId) {
        this.reservedByUserId = reservedByUserId;
    }
}
