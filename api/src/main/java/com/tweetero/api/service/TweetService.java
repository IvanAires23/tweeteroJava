package com.tweetero.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tweetero.api.dtos.TweetDTO;
import com.tweetero.api.models.TweetModel;
import com.tweetero.api.models.UserModel;
import com.tweetero.api.repositories.TweetRepository;
import com.tweetero.api.repositories.UserRepository;

@Service
public class TweetService {
    final TweetRepository tweetRepository;
    final UserRepository userRepository;

    TweetService(TweetRepository repository, UserRepository userRepository){
        this.tweetRepository = repository;
        this.userRepository = userRepository;
    }

    public Optional<TweetModel> save(TweetDTO dto){

        Optional<UserModel> user = userRepository.findById(dto.getUserId());

        if(!user.isPresent()){
            return Optional.empty();
        }

        TweetModel tweet = new TweetModel(dto, user.get());

        return Optional.of(tweetRepository.save(tweet));
    }

    public List<TweetModel> findAll() {
        List<TweetModel> tweets = tweetRepository.findAll();
        return tweets;
    }
}
