package com.sparta.spring_prepare.Service;

import com.sparta.spring_prepare.Entity.SiteUser;
import com.sparta.spring_prepare.Repository.UserRepository;
import com.sparta.spring_prepare.dto.UserCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // PasswordEncoder 주입

    // 회원가입 메소드
    public SiteUser register(UserCreateForm userCreateForm) {
        SiteUser user = new SiteUser();
        user.setUsername(userCreateForm.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateForm.getPassword())); // 비밀번호 해시화
        return userRepository.save(user); // DB에 저장
    }



}
