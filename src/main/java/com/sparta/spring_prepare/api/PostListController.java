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

    // 전체 게시물 조회
    @GetMapping
    public List<PostList> getAllPosts() {
        return postListService.findAll();
    }

    // 특정 게시물 조회
    @GetMapping("/{id}")
    public Optional<PostList> getPostById(@PathVariable Long id) {
        return postListService.findById(id);
    }

    // 게시물 생성
    @PostMapping
    public PostList createPost(@RequestBody PostList post) {
        return postListService.save(post);
    }

    // 게시물 수정
    @PutMapping("/{id}")
    public PostList updatePost(@PathVariable Long id, @RequestBody PostList postDetails) {
        PostList post = postListService.findById(id).orElseThrow();
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        post.setAuthor(postDetails.getAuthor());
        return postListService.save(post);
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postListService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
