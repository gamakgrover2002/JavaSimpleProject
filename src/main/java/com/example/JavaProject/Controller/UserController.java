package com.example.JavaProject.Controller;

import com.example.JavaProject.Entity.Product;
import com.example.JavaProject.Entity.User;
import com.example.JavaProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
       boolean status =  userService.saveCostumer(user);
       System.out.println(user);
        if(status){
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping
    public List<User> getAllProducts() {
        return userService.getAllProducts();
    }
}
