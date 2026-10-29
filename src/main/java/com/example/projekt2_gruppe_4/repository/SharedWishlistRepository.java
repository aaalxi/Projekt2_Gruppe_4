package com.example.projekt2_gruppe_4.repository;

import com.example.projekt2_gruppe_4.model.SharedWishlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SharedWishlistRepository {
    private final JdbcTemplate jdbcTemplate;




    public SharedWishlistRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }


    public void shareWishlist(int wishlistId, int userId){
        String sql="INSERT INTO sharedwishlist (wishlist_id, shared_with_user_id) " +
                "VALUES (?, ?)";
        jdbcTemplate.update(sql, wishlistId, userId);
    }
    //FINDS wishlists shared with THIS userId
    //JOIN table: sw = sharedwishlist, w = wishlist, u = user,
    public List<SharedWishlist> findSharedWishlistsByUserId(int userId){
        String sql= """
                SELECT sw.share_id, w.wishlist_id,
                sw.shared_with_user_id,
                w.title,
                u.name AS owner
                FROM SharedWishlist sw
                JOIN Wishlist w ON sw.wishlist_id = w.wishlist_id
                JOIN Users u ON w.user_id = u.user_id
                WHERE sw.shared_with_user_id = ?
                """;
        //COLLECTS THE VALUES FROM SQL, ADDS TO LIST AND RETURNS THE SHARED WISHLISTS
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new SharedWishlist(rs.getInt("share_id"),
                        rs.getInt("wishlist_id"),
                        rs.getInt("shared_with_user_id"),
                        rs.getString("title"),
                        rs.getString("owner")), userId);
    }









    //Returns true if conditions meet
    public boolean reserveWish (int wishId, int userId){
        String sql = """
                UPDATE Wish wish 
                JOIN SharedWishlist sw
                     ON w.wishlist_id = sw.wishlist_id
                SET wish.reserved_by_user_id = ?
                WHERE wish.wish_id = ?
                AND sw.shared_with_user_id = ?
                AND w.reserved_by_user_id IS NULL     
                """;
        return jdbcTemplate.update(sql, userId, wishId) ==1;
    }
}