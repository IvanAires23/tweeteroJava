package com.tweetero.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.tweetero.api.dtos.TweetDTO;
import com.tweetero.api.models.TweetModel;
import com.tweetero.api.service.TweetService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/tweets")
public class TweetController {

    final TweetService tweetService;

    TweetController(TweetService service){
        this.tweetService = service;
    }
    
    @PostMapping
    public ResponseEntity<Object> createTweet(@RequestBody @Valid TweetDTO dto) {
        Optional<TweetModel> tweet = tweetService.save(dto);

        if(!tweet.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(tweet);
    }

    @GetMapping
    public ResponseEntity<List<TweetModel>> findAllTweets(){

        List<TweetModel> tweets = tweetService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(tweets);
    }
}
