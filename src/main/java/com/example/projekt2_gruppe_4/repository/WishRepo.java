package com.example.projekt2_gruppe_4.repository;
import com.example.projekt2_gruppe_4.model.Wish;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class WishRepo {
    private final DataSource dataSource;

    public WishRepo(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void saveWish(Wish wish) {
        String sql = "INSERT INTO Wish (wishlist_id, title, description, product_url, price, wish_amount, img_url, priority) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection connect = dataSource.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql)) {

            ps.setInt(1, wish.getWishListId());
            ps.setString(2, wish.getTitle());
            ps.setString(3, wish.getDescription());
            ps.setString(4, wish.getProductUrl());
            ps.setBigDecimal(5, wish.getPrice());
            ps.setInt(6, wish.getAmount());
            ps.setString(7, wish.getImgUrl());
            ps.setObject(8, wish.getPriority());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Wish> getWishesByWishlistId(int wishlistId) {
        List<Wish> wishes = new ArrayList<>();
        String sql = "SELECT * From Wish WHERE wishlist_id = ?";

        try (Connection connect = dataSource.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, wishlistId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Wish wish = new Wish();
                    wish.setWishId(rs.getInt("wish_id"));
                    wish.setWishListId(rs.getInt("wishlist_id"));
                    wish.setTitle(rs.getString("title"));
                    wish.setDescription(rs.getString("description"));
                    wish.setProductUrl(rs.getString("product_url"));
                    wish.setPrice(rs.getBigDecimal("price"));
                    wish.setAmount(rs.getInt("wish_amount"));
                    wish.setImgUrl(rs.getString("img_url"));
                    wish.setPriority(rs.getObject("priority", Integer.class));
                    wish.setCreateDate(rs.getTimestamp("created_at").toLocalDateTime());
                    wish.setReservedByUserId(rs.getObject("reserved_by_user_id", Integer.class));
                    wishes.add(wish);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return wishes;
    }
}
