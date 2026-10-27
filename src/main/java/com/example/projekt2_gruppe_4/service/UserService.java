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


    public void reserveWish (){

    }
//using the BCrypt class method, .matches()
    public User login(String email, String password){

        User user = repository.findByEmail(email);
        if (user == null){
            System.out.println("Bruger ikke fundet, kig i service");
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



//Hashing the password before saving to db
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

    public List<User> getAll (){
        return repository.getAll();
    }
    public void sumOfWishes (User user){

    }


    public void sumOfWishlists (User user){

    }


    public User getById (int userId){
        return repository.findById(userId);
    }

    public void saveUser (User user){
        repository.saveUser(user);
    }

    public void deleteUser(int userId){
        repository.deleteById(userId);
    }
}