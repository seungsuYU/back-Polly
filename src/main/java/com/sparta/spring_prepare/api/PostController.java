package com.sparta.spring_prepare.api;

import com.sparta.spring_prepare.Entity.RequestPost;
import com.sparta.spring_prepare.Service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<RequestPost> createPost(@RequestBody RequestPost post) {
        RequestPost createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/list")
    public List<RequestPost> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<RequestPost> getPostById(@PathVariable Long id) {
        Optional<RequestPost> post = postService.getPostById(id);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestPost> updatePost(@PathVariable Long id, @RequestBody RequestPost updatedPost) {
        RequestPost post = postService.updatePost(id, updatedPost);
        return ResponseEntity.ok(post);
    }
}

