package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByFirstNameContaining(String firstName);
    List<User> findByFirstNameContains(String firstName);
    List<User> findByFirstNameIsContaining(String firstName);

    List<User> findByFirstNameStartsWith(String firstName);
    List<User> findByFirstNameEndsWith(String firstName);
    List<User> findByFirstNameContainingIgnoreCase(String firstName);
}
