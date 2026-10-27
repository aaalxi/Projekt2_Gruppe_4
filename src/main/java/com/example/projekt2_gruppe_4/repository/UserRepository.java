package com.example.projekt2_gruppe_4.repository;

import com.example.projekt2_gruppe_4.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class UserRepository {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;


    private RowMapper<User> users = (rs, rowNum) ->
            new User (rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password_hash"),
                    rs.getTimestamp("user_create_date").toLocalDateTime()
            );                                               //converting timestamp to local DT


    public UserRepository (DataSource dataSource, JdbcTemplate jdbcTemplate){
        this.dataSource=dataSource;
        this.jdbcTemplate=jdbcTemplate;
    }


    public List<User> getAll (){
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, users);
    }       //.query and .queryForObject gets value/s, use .update() for insert, update and delete

    public User findByEmail (String email){
        String sql = "SELECT * FROM users WHERE email = ?";
        try{
            return jdbcTemplate.queryForObject(sql, users, email);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public User findById(int userId){
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try(Connection connection = dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, userId);

            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return new User(rs.getInt("user_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password_hash"),
                            rs.getTimestamp("user_create_date").toLocalDateTime());
                }
            }
        } catch (Exception e){
            System.out.println("Error findByUserId");
        }
        return null;
    }

    public void saveUser (User user){
        String sql = "INSERT INTO Users (name, email, password_hash)" +
                "VALUES (?, ?, ?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());

            stmt.executeUpdate();

        } catch (Exception e){
            System.out.println("Didn't SAVE User");
            e.printStackTrace();
        }
    }


    public int deleteUser (int userId){
        String sql="DELETE FROM users WHERE user_id =?";
        return jdbcTemplate.update(sql, userId);
    } //returns how many rows deleted


    public void reserveWish (int wishlistId, int userId){
        String sql ="UPDATE wish" +
                "SET reserved_by_user_id = ?" +
                "WHERE wish_id = ? " +
                "AND reserved_by_user_is IS NULL";
        jdbcTemplate.update(sql, wishlistId, userId);   //args = parameters send to template
    }

}