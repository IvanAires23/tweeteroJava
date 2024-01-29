package com.tweetero.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetero.api.models.TweetModel;

@Repository
public interface TweetRepository extends JpaRepository<TweetModel, Long> {
    
}
