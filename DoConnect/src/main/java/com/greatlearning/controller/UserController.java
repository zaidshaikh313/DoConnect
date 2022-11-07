package com.greatlearning.controller;

import com.greatlearning.Dto.SignUpRequest;
import com.greatlearning.Dto.UserDto;
import com.greatlearning.entities.User;
import com.greatlearning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping("/signUp")
    public UserDto signUp(@RequestBody SignUpRequest signUpRequest){
        return userService.signup(signUpRequest);

    }
    @GetMapping("")
    public ResponseEntity<List<User>> get(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getUser/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }


}
