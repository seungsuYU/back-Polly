package com.sparta.spring_prepare.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private int price;
    private LocalDate tradeDate;
    private String tradeTime;
    private String location;
    private byte[] image;
    private String username;  // 작성자 이름
}

