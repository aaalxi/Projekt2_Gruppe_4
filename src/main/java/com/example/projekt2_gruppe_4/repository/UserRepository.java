package com.example.projekt2_gruppe_4.repository;

import com.example.projekt2_gruppe_4.model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class UserRepository {
    private final DataSource dataSource;                //Connection object

    public UserRepository (DataSource dataSource){      //Autowired, injection
        this.dataSource=dataSource;
    }



    public void save (User user){
        String sql = "INSERT INTO Users (name, email, password_hash)" +
                "VALUES (?, ?, ?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);) {

            stmt.setString(1, "name");
            stmt.setString(2, "email");
            stmt.setString(3, "password");

            stmt.executeUpdate();                       //auto-commit is true as default

        } catch (Exception e){
            System.out.println("Didn't SAVE User");
        }

    }
}