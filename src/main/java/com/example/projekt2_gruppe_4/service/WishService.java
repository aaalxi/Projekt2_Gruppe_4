package com.example.projekt2_gruppe_4.service;
import com.example.projekt2_gruppe_4.model.Wish;
import com.example.projekt2_gruppe_4.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class WishService {
    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository){
        this.wishRepository = wishRepository;
    }

    public void editWish(Wish wish){
        wishRepository.update(wish);
    }
}
