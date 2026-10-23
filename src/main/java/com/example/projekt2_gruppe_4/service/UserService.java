package com.example.projekt2_gruppe_4.service;

import com.example.projekt2_gruppe_4.model.User;
import com.example.projekt2_gruppe_4.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;   //final: cannot be changed once assigned anywhere in the code


    public UserService(UserRepository repository){
        this.repository=repository;
    }

    public void addUser (User user){
        repository.saveUser(user);
    }

    public List<User> getAll (){
        return repository.getAll();
    }

    public User getById (int userId){
        return repository.findById(userId);
    }

}