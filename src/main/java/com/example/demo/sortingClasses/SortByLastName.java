package com.example.demo.sortingClasses;

import com.example.demo.model.User;

import java.util.Comparator;

public class SortByLastName implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        return user1.getLastName().compareTo(user2.getLastName());
    }

}
