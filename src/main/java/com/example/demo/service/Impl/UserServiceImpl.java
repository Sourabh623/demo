package com.example.demo.service.Impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

        u1.setPassword(user.getPassword());

        //set the pattern of creation time
        LocalDateTime current = LocalDateTime.now();
        System.out.println("current date and time : "+current);

        // to print in a particular format
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = current.format(format);
        System.out.println("in formatted manner "+ formattedDateTime);

        //u1.setCreationDate(formattedDateTime);
        //u1.setUpdationDate(formattedDateTime);
        System.out.println("new data object "+new Date());
        u1.setCreationDate(new Date());
        u1.setUpdationDate(new Date());
        return this.userRepository.save(u1);
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id", userId));
    }

    public void deleteUser(Long userId) {

        //getting the user by userId from db
        User user = this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id", userId));

        //delete the user into the db
        this.userRepository.delete(user);
    }

    @Override
    public User updateUser(User user, Long userId) {

        //getting the user by userId from db
        User myUser = this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id", userId));

        myUser.setFirstName(user.getFirstName());
        myUser.setLastName(user.getLastName());
        myUser.setUserEmail(user.getUserEmail());
        myUser.setUpdationDate(new Date());
        return this.userRepository.save(myUser);
    }
}
