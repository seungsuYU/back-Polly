package com.sparta.spring_prepare.Repository;

import com.sparta.spring_prepare.Entity.PostList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PostListRepository extends JpaRepository<PostList, Long> {
    @Override
    ArrayList<PostList> findAll();
}

