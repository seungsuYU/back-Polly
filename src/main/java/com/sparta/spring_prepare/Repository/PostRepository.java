package com.sparta.spring_prepare.Repository;

import com.sparta.spring_prepare.Entity.RequestPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<RequestPost, Long> {
}
