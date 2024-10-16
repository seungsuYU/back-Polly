package com.sparta.spring_prepare.dto;

import com.sparta.spring_prepare.Entity.PostList;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostListForm {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostList toEntity() {
        return new PostList(id, title, content, author);
    }
}
