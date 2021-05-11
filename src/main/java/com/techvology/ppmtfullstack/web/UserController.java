package com.techvology.ppmtfullstack.web;

import com.techvology.ppmtfullstack.domain.User;
import com.techvology.ppmtfullstack.services.MapValidationErrorService;
import com.techvology.ppmtfullstack.services.UserService;
import com.techvology.ppmtfullstack.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final MapValidationErrorService mapValidationErrorService;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UserService userService, MapValidationErrorService mapValidationErrorService, UserValidator userValidator){
        this.userService = userService;
        this.mapValidationErrorService = mapValidationErrorService;
        this.userValidator = userValidator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user
                                          , BindingResult result){
        userValidator.validate(user, result);

        ResponseEntity<?>errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        User newUser = userService.saveUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}
