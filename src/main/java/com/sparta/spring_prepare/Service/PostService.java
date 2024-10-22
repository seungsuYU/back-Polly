package com.sparta.spring_prepare.Service;

import com.sparta.spring_prepare.Entity.RequestPost;
import com.sparta.spring_prepare.Repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public RequestPost createPost(RequestPost post) {
        return postRepository.save(post);
    }

    public List<RequestPost> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<RequestPost> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public RequestPost updatePost(Long id, RequestPost updatedPost) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(updatedPost.getTitle());
                    post.setContent(updatedPost.getContent());
                    post.setPrice(updatedPost.getPrice());
                    post.setTradeTime(updatedPost.getTradeTime());
                    post.setTradeDate(updatedPost.getTradeDate());
                    post.setLocation(updatedPost.getLocation());
                    post.setImage(updatedPost.getImage());
                    return postRepository.save(post);
                }).orElseThrow(() -> new IllegalArgumentException("Post not found with id " + id));
    }
}
