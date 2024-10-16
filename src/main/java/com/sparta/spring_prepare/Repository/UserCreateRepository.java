package com.sparta.spring_prepare.Repository;

import com.sparta.spring_prepare.Entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCreateRepository extends JpaRepository <SiteUser, Long>{
    Optional<SiteUser> findByusername(String username);
}
