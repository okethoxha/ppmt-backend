package com.techvology.ppmtfullstack.services;

import com.techvology.ppmtfullstack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;  //TODO: Constructor injection to be done
}
