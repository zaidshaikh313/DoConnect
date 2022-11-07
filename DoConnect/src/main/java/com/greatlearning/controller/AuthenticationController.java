package com.greatlearning.controller;

import com.greatlearning.Dto.AuthenticationRequest;
import com.greatlearning.Dto.AuthenticationResponse;
import com.greatlearning.exceptions.InvalidUserCredentialsException;
import com.greatlearning.repositories.UserRepos;
import com.greatlearning.services.UserService;
import com.greatlearning.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/signIn")
public class AuthenticationController {
    @Autowired
    private UserRepos userRepos;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest userRequest) {
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword());
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new InvalidUserCredentialsException("Invalid Credentials");
        }
        User user = (User) authentication.getPrincipal();
        com.greatlearning.entities.User user1=userService.getUser(userRequest.getUsername());

        String roles= user1.getRole();
        String token=jwtUtil.generateToken(authentication);
        AuthenticationResponse userResponse = new AuthenticationResponse();
        userResponse.setToken(token);
        userResponse.setRole(roles);
        userResponse.setId(user1.getUser_id());
        return new ResponseEntity<AuthenticationResponse>(userResponse, HttpStatus.OK);
    }

}
