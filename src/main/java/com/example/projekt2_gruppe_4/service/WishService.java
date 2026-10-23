package com.example.projekt2_gruppe_4.service;
import com.example.projekt2_gruppe_4.model.Wish;
import com.example.projekt2_gruppe_4.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WishService {
    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository){
        this.wishRepository = wishRepository;
    }

    private void validateTitle(Wish wish){
        if(wish.getTitle() == null || wish.getTitle().isBlank()){
            throw new IllegalArgumentException("Ønsket skal have en title.");
        }
    }

    private void validatePrice(Wish wish){
        if(wish.getPrice() != null && wish.getPrice().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Prisen kan ikke være minus.");
        }
    }

    private void validateAmount(Wish wish){
        if(wish.getAmount() <= 0){
            throw new IllegalArgumentException("Skal skrive et antal ønsker");
        }
    }

    public void createWish(Wish wish){
        validateTitle(wish);
        validatePrice(wish);
        validateAmount(wish);
        wishRepository.saveWish(wish);
    }

    public List<Wish> getWishByWishlistId(int wishlistId) {
        return wishRepository.getWishesByWishlistId(wishlistId);
    }
}