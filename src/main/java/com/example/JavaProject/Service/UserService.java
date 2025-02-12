package com.example.JavaProject.Service;

import com.example.JavaProject.DTO.Response.RegisterDTO;
import com.example.JavaProject.Entity.User;
import com.example.JavaProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    @Lazy
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    public boolean saveCostumer(User user){
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
       User savedCustomer =  userRepository.save(user);
        return savedCustomer.getId() != null;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority("ROLE_USER")) // Assign default role
                .build();

    }
    public User RegisterDTOToUser(RegisterDTO registerDTO){
        User newUser = new User();
              newUser.setUserName(registerDTO.getUserName());
              newUser.setName(registerDTO.getUserName());
              newUser.setPassword(registerDTO.getPassword());
              return newUser;
    }


}
