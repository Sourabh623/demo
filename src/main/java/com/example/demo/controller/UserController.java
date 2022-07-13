package com.example.demo.controller;


import com.example.demo.exception.FieldNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    //create an user
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User saveUser = this.userService.createUser(user);
        return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
    }

    //get all users
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUser()
    {
        return ResponseEntity.ok(this.userService.getAllUser());
    }

//    //sort by firstName
//    @GetMapping("/sortByFirstName")
//    public ResponseEntity<List<User>> sortByFirstName(){
//        List<User> userList = this.userRepository.findAll();
//        Collections.sort(userList, new SortByFirstName());
//        return ResponseEntity.ok(userList);
//    }

//    //sort by firstName
//    @GetMapping("/sortByFirstName/{firstName}")
//    public ResponseEntity<List<User>> sortByFirstNameUsingCustomFinderMethod(@PathVariable(value = "firstName")String firstName){
//        List<User> userList = this.userRepository.findByFirstNameContains(firstName);   //jitne bhi start me char hai ye vo char contain krta hai to return kr dega vo
//        //Collections.sort(userList, new SortByFirstName());
//        return ResponseEntity.ok(userList);
//    }

//    //sort by firstName
//    @GetMapping("/sortByFirstName/{firstName}")
//    public ResponseEntity<List<User>> sortByFirstNameUsingCustomFinderMethod1(@PathVariable(value = "firstName")String firstName){
//        List<User> userList = this.userRepository.findByFirstNameContaining(firstName);   //jitne bhi start me char hai ye vo char contain krta hai to return kr dega vo
//        //Collections.sort(userList, new SortByFirstName());
//        return ResponseEntity.ok(userList);
//    }

//    //sort by firstName
//    @GetMapping("/sortByFirstName/{firstName}")
//    public ResponseEntity<List<User>> sortByFirstNameUsingCustomFinderMethod1(@PathVariable(value = "firstName")String firstName){
//        List<User> userList = this.userRepository.findByFirstNameIsContaining(firstName);   //jitne bhi start me char hai ye vo char contain krta hai to return kr dega vo
//        //Collections.sort(userList, new SortByFirstName());
//        return ResponseEntity.ok(userList);
//    }

//    //sort by firstName
//    @GetMapping("/sortByFirstName/{firstName}")
//    public ResponseEntity<List<User>> sortByFirstNameUsingCustomFinderMethod2(@PathVariable(value = "firstName")String firstName){
//        List<User> userList = this.userRepository.findByFirstNameStartsWith(firstName);
//        return ResponseEntity.ok(userList);
//    }

//    //sort by firstName
//    @GetMapping("/sortByFirstName/{firstName}")
//    public ResponseEntity<List<User>> sortByFirstNameUsingCustomFinderMethod3(@PathVariable(value = "firstName")String firstName){
//        List<User> userList = this.userRepository.findByFirstNameEndsWith(firstName);
//        return ResponseEntity.ok(userList);
//    }

 //sort by firstName
    @GetMapping("/sortByFirstName")
    public ResponseEntity<List<User>> sortByFirstNameUsingCustomFinderMethod3(
            @RequestParam(value = "sortDirection")String sortDirection,
            @RequestParam(value = "field1")String field1,
            @RequestParam(value = "field2")String field2){
        List<User> userList;
        try{
            if(Objects.equals(sortDirection, "DESC")) userList = this.userRepository.findAll(Sort.by(Sort.Direction.DESC, field1, field2));
            else userList = this.userRepository.findAll(Sort.by(Sort.Direction.ASC, field1, field2));
            return ResponseEntity.ok(userList);
        }catch (Exception ex){
            System.out.println(ex.toString());
            throw new FieldNotFoundException(ex.getMessage());
        }

    }
    @GetMapping("/sortByBothDirections")
    public List<User> getAllByDirections(@RequestParam(name = "field1", defaultValue = "firstName",required = true) String field1, @RequestParam(name = "field2", defaultValue = "firstName",required = false) String field2)
    {
        List<Sort.Order> orders = new ArrayList<>();
        try{
        orders.add(new Sort.Order(Sort.Direction.ASC, field1));
        orders.add(new Sort.Order(Sort.Direction.DESC, field2));
        return this.userRepository.findAll(Sort.by(orders));
        }catch (Exception ex){
            System.out.println(ex.toString());
            throw new FieldNotFoundException(ex.getMessage());
        }
    }


}
