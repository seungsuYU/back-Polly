package com.sparta.spring_prepare.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostRequestDto {
        private String title;
        private String content;
        private int price;
        private LocalDateTime desiredTime;   // 거래희망시간
        private String desiredDate;   // 거래희망날짜
        private String location;       // 거래지역
        private String imageUrl;       // 이미지 URL
    }
