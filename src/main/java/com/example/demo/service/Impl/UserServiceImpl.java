package com.example.demo.service.Impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        User u1 = new User();
        u1.setFirstName(user.getFirstName());
        u1.setLastName(user.getLastName());
        u1.setUserEmail(user.getUserEmail());
        u1.setLastName(user.getLastName());
        return this.userRepository.save(u1);
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }
}
