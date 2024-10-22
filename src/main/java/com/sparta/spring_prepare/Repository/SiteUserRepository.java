package com.sparta.spring_prepare.Repository;

import com.sparta.spring_prepare.Entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
    // 사용자 이름으로 사용자 찾기
    Optional<SiteUser> findByUsername(String username);
}
