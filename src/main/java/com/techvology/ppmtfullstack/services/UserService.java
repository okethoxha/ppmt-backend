package com.techvology.ppmtfullstack.services;

import com.techvology.ppmtfullstack.domain.User;
import com.techvology.ppmtfullstack.exceptions.UsernameAlreadyExistsException;
import com.techvology.ppmtfullstack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }



    public User saveUser(User newUser){
        try {
            newUser.setUsername(newUser.getUsername());
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            return userRepository.save(newUser);
        }catch (Exception ex) {
            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists!");
        }
    }
}
