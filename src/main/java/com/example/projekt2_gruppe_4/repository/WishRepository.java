package com.example.projekt2_gruppe_4.repository;
import com.example.projekt2_gruppe_4.model.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class WishRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Wish> WISH_MAPPER = (rs, rowNum) -> (
            new Wish(rs.getInt("wish_id"),
            rs.getInt("wishlist_id"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getString("product_url"),
            rs.getBigDecimal("price"),
            rs.getInt("wish_amount"),
            rs.getString("img_url"),
            rs.getInt("priority"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getInt("reserved_by_user_id")));

    private final DataSource dataSource;


    public WishRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }


    public void saveWish(Wish wish) {
        String sql = """
                INSERT INTO Wish (wishlist_id,
                title,description,
                product_url,
                price,
                wish_amount,
                img_url,
                priority)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;


        try (Connection connect = dataSource.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql)) {

            ps.setInt(1, wish.getWishlistId());
            ps.setString(2, wish.getTitle());
            ps.setString(3, wish.getDescription());
            ps.setString(4, wish.getProductUrl());
            ps.setBigDecimal(5, wish.getPrice());
            ps.setInt(6, wish.getAmount());
            ps.setString(7, wish.getImgUrl());
            ps.setInt(8, wish.getPriority());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Wish findWishById(int wishId) {
        String sql = "SELECT * From Wish WHERE wish_id = ?";
        Wish wish = new Wish();

        try (Connection connect = dataSource.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, wishId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    wish.setWishId(rs.getInt("wish_id"));
                    wish.setWishlistId(rs.getInt("wishlist_id"));
                    wish.setTitle(rs.getString("title"));
                    wish.setDescription(rs.getString("description"));
                    wish.setProductUrl(rs.getString("product_url"));
                    wish.setPrice(rs.getBigDecimal("price"));
                    wish.setAmount(rs.getInt("wish_amount"));
                    wish.setImgUrl(rs.getString("img_url"));
                    wish.setPriority(rs.getInt("priority"));
                    wish.setCreateDate(rs.getTimestamp("created_at").toLocalDateTime());
                    wish.setReservedByUserId(rs.getInt("reserved_by_user_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return wish;
    }

    public void update(Wish wish){
        String sql = """
                UPDATE Wish SET title = ?,
                description = ?,
                product_url = ?,
                price = ?,
                wish_amount = ?,
                img_url = ?,
                priority = ?
                WHERE wish_id = ?
                """;
        jdbcTemplate.update(sql, wish.getTitle(),
                wish.getDescription(),
                wish.getProductUrl(),
                wish.getPrice(),
                wish.getAmount(),
                wish.getImgUrl(),
                wish.getPriority());
    }

    public void deleteById(int wishId){
        String sql = "DELETE FROM wish WHERE wish_id = ?";
        jdbcTemplate.update(sql, wishId);
    }

    public List<Wish> findByWishlistId(int wishlistId){
        String sql = "SELECT * FROM Wishlist WHERE wishlist_id = ?";
        return jdbcTemplate.query(sql, WISH_MAPPER, wishlistId);
    }
}
