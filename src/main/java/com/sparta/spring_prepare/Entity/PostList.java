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
    private String author;

    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    public PostList(Long id, String title, String content, String author) {
        this(id, title, content, author, LocalDateTime.now(), LocalDateTime.now());
    }


    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = LocalDateTime.now();
    }

    public void patch(PostList postList) {
        if (postList.title != null)
            this.title = postList.title;
        if (postList.content != null)
            this.content = postList.content;
    }
}
