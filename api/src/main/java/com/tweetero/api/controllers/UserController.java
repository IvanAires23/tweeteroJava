package com.tweetero.api.controllers;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetero.api.dtos.UserDTO;
import com.tweetero.api.models.UserModel;
import com.tweetero.api.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    final UserRepository userRepository;
    
    UserController(UserRepository repository){
        this.userRepository = repository;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserDTO body){

        ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnorePaths("id");

        Example<UserModel> example = Example.of(new UserModel(body), matcher);

        Optional<UserModel> userRepeated = userRepository.findOne(example);

        if(!userRepeated.isPresent()){
            UserModel user = new UserModel(body);
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario já existe");
        }
    }
    
}
