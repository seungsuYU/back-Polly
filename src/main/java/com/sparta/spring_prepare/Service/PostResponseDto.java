package com.sparta.spring_prepare.Service;

import com.sparta.spring_prepare.Entity.RequestPost;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class PostResponseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private int price;
    private LocalTime tradeTime;
    private LocalDate tradeDate;
    private String location;
    private UserDto user;

    public PostResponseDto(RequestPost post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.price = post.getPrice();
        this.tradeTime = post.getTradeTime();
        this.tradeDate = post.getTradeDate();
        this.location = post.getLocation();
        this.user = new UserDto(post.getUser());
    }
}
