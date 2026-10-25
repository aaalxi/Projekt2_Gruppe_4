package com.example.projekt2_gruppe_4.repository;

import com.example.projekt2_gruppe_4.model.User;
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
    }

    public User findByEmail (String email){
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, users, email);
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
            PreparedStatement stmt = connection.prepareStatement(sql);) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());

            stmt.executeUpdate();

        } catch (Exception e){
            System.out.println("Didn't SAVE User");
            e.printStackTrace();
        }
    }
}