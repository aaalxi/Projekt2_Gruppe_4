package com.example.projekt2_gruppe_4.repository;

import com.example.projekt2_gruppe_4.model.Wishlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Wishlist> WISHLIST_MAPPER = (rs, rowNum) -> (
            new Wishlist(rs.getInt("wishlist_id"),
                    rs.getInt("user_id"),
                    rs.getString("title"),
            rs.getTimestamp("created_at").toLocalDateTime()));

    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Wishlist findById(int wishlistId){
        String sql = "SELECT * FROM Wishlist WHERE wishlist_id=?";
        return jdbcTemplate.queryForObject(sql, WISHLIST_MAPPER, wishlistId);
    }

    public List<Wishlist> findByUserId(int userId){
        String sql = "SELECT * FROM Wishlist WHERE user_id=?";
        return jdbcTemplate.query(sql, WISHLIST_MAPPER, userId);
    }

    public void create(Wishlist wishlist){
        String sql = "INSERT INTO Wishlist(user_id, title) VALUES(?,?)";
        jdbcTemplate.update(sql,
                wishlist.getUserId(),
                wishlist.getTitle());
    }
    public void deleteById(int wishlistId){
        String sql = "DELETE FROM Wishlist WHERE wishlist_id = ?";
        jdbcTemplate.update(sql, wishlistId);
    }
}
