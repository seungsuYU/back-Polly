package com.sparta.spring_prepare.api;


import com.sparta.spring_prepare.Entity.PostList;
import com.sparta.spring_prepare.Service.PostListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostListController {

    @Autowired
    private PostListService postListService;

    @GetMapping
    public List<PostList> getAllPosts() {
        return postListService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<PostList> getPostById(@PathVariable Long id) {
        return postListService.findById(id);
    }

    @PutMapping("/{id}")
    public PostList updatePost(@PathVariable Long id,
                               @RequestBody PostList postDetatils) {
        PostList postList = postListService.findById(id).orElseThrow();
        postList.setTitle(postDetatils.getTitle());
        postList.setContent(postList.getContent());
        postList.setWriter(postList.getWriter());
        return postListService.save(postList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postListService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
