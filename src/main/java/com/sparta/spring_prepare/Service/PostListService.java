package com.sparta.spring_prepare.Service;

import com.sparta.spring_prepare.Entity.PostList;
import com.sparta.spring_prepare.Repository.PostListRepository;
import com.sparta.spring_prepare.dto.PostListForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostListService {

    @Autowired
    private PostListRepository postListRepository;

    public List<PostList> index() {
        return postListRepository.findAll();
    }

    public PostList show(Long id) {
      log.info("Service");
        return postListRepository.findById(id).orElse(null);
    }

    public PostList create(PostListForm dto) {
        PostList postList = dto.toEntity();
        if (postList.getId() != null) {
            return null;
        }
        return postListRepository.save(postList);
    }

    public PostList update(Long id, PostListForm dto) {
        PostList postList = dto.toEntity();
        PostList target = postListRepository.findById(id).orElse(null);
        if (target == null || id != postList.getId()) {
            return null;
        }
        target.patch(postList);
        PostList updated = postListRepository.save(target);
        return updated;
    }
    }
