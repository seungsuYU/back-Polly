package com.sparta.spring_prepare.api;

import com.sparta.spring_prepare.Entity.PostList;
import com.sparta.spring_prepare.Service.PostListService;
import com.sparta.spring_prepare.dto.PostListForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostListController {

    @Autowired
    private PostListService postListService;

    // 전체 게시물 조회
    @GetMapping("/list")
    public List<PostList> index() {
        return postListService.index();
    }

    // 특정 게시물 조회
    @GetMapping("/{id}")
    public PostList show(@PathVariable Long id) {
        log.info("controlleraa");
        return postListService.show(id);
    }

    // 게시물 생성
    @PostMapping("/create")
    public ResponseEntity<PostList> create(@RequestBody PostListForm dto) {
        PostList created = postListService.create(dto);
        return (created != null) ?
        ResponseEntity.status(HttpStatus.OK).body(created) :
        ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 게시물 수정
    @PutMapping("/{id}")
    public ResponseEntity<PostList> update(@PathVariable("id") Long id,
                           @RequestBody PostListForm dto) {
        PostList updated = postListService.update(id,dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
