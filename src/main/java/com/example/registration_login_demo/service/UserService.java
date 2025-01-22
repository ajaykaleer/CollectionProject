package com.example.registration_login_demo.service;


import aj.org.objectweb.asm.commons.Remapper;
import com.example.registration_login_demo.entity.User;
import com.example.registration_login_demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo,PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    public User registerUser(String email,String password){
        if(userRepo.findByEmail(email).isPresent()){
            throw new IllegalArgumentException("user with email" +email+" already exists");

        }
        User user =new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepo.save(user);

    }
    public Optional<User> findUserByEmail(String email){
        return userRepo.findByEmail(email);
    }
}
