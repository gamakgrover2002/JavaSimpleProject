package com.example.JavaProject.Controller;

import com.example.JavaProject.Entity.Product;
import com.example.JavaProject.Entity.User;
import com.example.JavaProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    @Autowired
    private  UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
       boolean status =  userService.saveCostumer(user);
        if(status){
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user){
        System.out.println(user);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        System.out.println(token);
       Authentication auth =  authenticationManager.authenticate(token);
       System.out.println("Authitication completed");
        System.out.println(auth);
       boolean status = auth.isAuthenticated();
       System.out.println(status);
       if(status){
           return new ResponseEntity<>("Ok",HttpStatus.OK);
       }
       return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);

    }
    @GetMapping
    public List<User> getAllProducts() {
        return userService.getAllProducts();
    }
}
