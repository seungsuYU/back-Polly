package com.sparta.spring_prepare.Repository;

import com.sparta.spring_prepare.Entity.PostList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostListRepository extends JpaRepository<PostList, Long> {
    List<PostList> findByTitle(String title);
}
