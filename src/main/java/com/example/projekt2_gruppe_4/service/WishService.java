package com.example.projekt2_gruppe_4.service;
import com.example.projekt2_gruppe_4.model.Wish;
import com.example.projekt2_gruppe_4.repository.WishRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class WishService {
    private final WishRepo wishRepo;

    public WishService(WishRepo wishRepo){
        this.wishRepo = wishRepo;
    }
}
