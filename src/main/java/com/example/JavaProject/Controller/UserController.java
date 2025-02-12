package com.example.JavaProject.Controller;


import com.example.JavaProject.DTO.Response.LoginDTO;
import com.example.JavaProject.DTO.Response.RegisterDTO;
import com.example.JavaProject.Entity.User;
import com.example.JavaProject.Service.JwtService;
import com.example.JavaProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController

public class UserController {

    @Autowired
    private  UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwt;

   @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO){
        User newUser = userService.RegisterDTOToUser(registerDTO);
       boolean status =  userService.saveCostumer(newUser);
        if(status){
           String token = jwt.generateToken(registerDTO.getUserName());
           System.out.println(token);
            return new ResponseEntity<>(Map.of("message", "Success", "token", token), HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO logindto){

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(logindto.getUserName(), logindto.getPassword());

       Authentication auth =  authenticationManager.authenticate(token);

       boolean status = auth.isAuthenticated();

       if(status){
           return new ResponseEntity<>("Ok",HttpStatus.OK);
       }
       return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);

    }
    @GetMapping
    public List<User> getAllProducts() {
        return userService.getAllUsers();
    }
}
