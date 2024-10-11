package com.sparta.spring_prepare.Service;

import com.sparta.spring_prepare.Entity.PostList;
import com.sparta.spring_prepare.Repository.PostListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostListService {

    @Autowired
    private PostListRepository postListRepository;

    public List<PostList> findAll() {
        return postListRepository.findAll();
    }

    public Optional<PostList> findById (Long id) {
        return postListRepository.findById(id);
    }

    public PostList save(PostList postList) {
        return postListRepository.save(postList);
    }

    public void delete(Long id) {
        postListRepository.deleteById(id);
    }
}
