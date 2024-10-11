package com.sparta.spring_prepare.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate() {
        this.updateDate = LocalDateTime.now();
    }


}
