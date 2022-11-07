package com.greatlearning.services;

import com.greatlearning.Dto.SignUpRequest;
import com.greatlearning.Dto.UserDto;
import com.greatlearning.entities.User;

import com.greatlearning.exceptions.UserAlreadyExistsException;
import com.greatlearning.repositories.UserRepos;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service("userService")
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepos userRepos;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public boolean userExists(String username) {
        return userRepos.findByUsername(username) != null;
    }


    public User getUser(String username) {
        return userRepos.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    public List<User> getAll(){
        return userRepos.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {


        User user = userRepos.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

      return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());

    }

    public UserDto signup(SignUpRequest signUpRequest) {
        if (userRepos.findByUsername(signUpRequest.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User user = new User();

        user.setEmail(signUpRequest.getEmail());
        user.setOccupation(signUpRequest.getOccupation());
        user.setRole(signUpRequest.getRole());
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepos.save(user);
        return convertUserToUserDto(user);


    }

    public UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto(user.getUser_id(), user.getUsername(), user.getRole());
        return userDto;
    }
}
