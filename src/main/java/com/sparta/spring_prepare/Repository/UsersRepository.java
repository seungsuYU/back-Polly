package com.sparta.spring_prepare.Repository;

import com.sparta.spring_prepare.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
}
