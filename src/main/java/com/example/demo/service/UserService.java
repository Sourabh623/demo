package com.example.demo.service;


import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    //create an user
    User createUser(User user);

    List<User> getAllUser();
}
