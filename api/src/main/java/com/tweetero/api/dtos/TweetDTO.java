package com.tweetero.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TweetDTO {

    @NotBlank
    private String text;
    
    private Long userId;
    
} 
