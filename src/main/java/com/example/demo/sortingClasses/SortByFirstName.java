package com.example.demo.sortingClasses;

import com.example.demo.model.User;

import java.util.Comparator;

public class SortByFirstName implements Comparator<User>{
    @Override
    public int compare(User user1, User user2) {
        return user1.getFirstName().compareTo(user2.getFirstName());
    }

}
