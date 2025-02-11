package com.example.JavaProject.Service;

import com.example.JavaProject.Entity.Product;
import com.example.JavaProject.Entity.User;
import com.example.JavaProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    public boolean saveCostumer(User user){
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
       User savedCustomer =  userRepository.save(user);
        return savedCustomer.getId() != null;
    }
    public List<User> getAllProducts() {
        return userRepository.findAll();
    }


}
