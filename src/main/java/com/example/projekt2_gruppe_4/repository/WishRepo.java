package com.example.projekt2_gruppe_4.repository;
import com.example.projekt2_gruppe_4.model.Wish;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Repository
public class WishRepo {


    public void saveWish(Wish wish){
        String sql = "INSERT INTO Wish (wishlist_id, title, description, product_url, price, wish_amount, img_url, priority) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connect = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Onskeskyen", "root", "");
            PreparedStatement ps = connect.prepareStatement(sql)) {

            ps.setInt(1, wish.getWishListId());
            ps.setString(2, wish.getTitle());
            ps.setString(3, wish.getDescription());
            ps.setString(4, wish.getProductUrl());
            ps.setBigDecimal(5, wish.getPrice());
            ps.setInt(6, wish.getAmount());
            ps.setString(7, wish.getImgUrl());
            ps.setObject(8,wish.getPriority());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
