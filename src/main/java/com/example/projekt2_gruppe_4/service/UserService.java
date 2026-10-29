package com.example.projekt2_gruppe_4.service;

import com.example.projekt2_gruppe_4.model.User;
import com.example.projekt2_gruppe_4.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncorder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder){
        this.repository=repository;
        this.passwordEncorder=passwordEncoder;
    }

    public User getById (int userId){
        return repository.findById(userId);
    }

    public User getByEmail(String email){
        return repository.findByEmail(email);
    }

    public void addUser (User user){
        String hashedPassword = passwordEncorder.encode(user.getPasswordHash());
        user.setPasswordHash(hashedPassword);
        repository.saveUser(user);
    }

    //confirms and calls repo to delete, returns a boolean, true if 1 user is deleted
    public boolean deleteUser (int userId, String password){
        User user = repository.findById(userId);

        if (user == null){
            return false;
        }
        if (!passwordEncorder.matches(password, user.getPasswordHash())){
            return false;
        }
        int rowsDeleted = repository.deleteUser(userId);
        return rowsDeleted == 1;
    }

//using the bcrypt class method .matches()
    public User login(String email, String password){

        User user = repository.findByEmail(email);
        if (user == null){
            return null;
        }
        boolean allowedAccess = passwordEncorder.matches(
                password, user.getPasswordHash());
        System.out.println("Password matcher: " + allowedAccess);
        if (allowedAccess){
            return user;
        }
        return null;
    }
}