package com.sparta.spring_prepare.Repository;

import com.sparta.spring_prepare.Entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    SiteUser findByUsername(String username); // 사용자 이름으로 사용자 찾기
}
