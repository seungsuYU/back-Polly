package com.sparta.spring_prepare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequesterPostDto {
    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @NotNull(message = "가격은 필수입니다.")
    private Double price;

    @NotBlank(message = "거래 희망 시간은 필수입니다.")
    private String desiredTime;

    @NotBlank(message = "거래 희망 날짜는 필수입니다.")
    private String desiredDate;

    @NotBlank(message = "거래 지역은 필수입니다.")
    private String location;

    @NotBlank(message = "이미지는 필수입니다.")
    private String imageUrl;

    // 기본 생성자 및 Getter/Setter 생략
}
