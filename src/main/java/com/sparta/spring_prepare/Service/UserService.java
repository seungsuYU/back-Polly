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
    private PasswordEncoder passwordEncoder;

    // 회원가입 메소드
    public SiteUser register(UserCreateForm userCreateForm) {
        SiteUser user = new SiteUser();
        user.setUsername(userCreateForm.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateForm.getPassword()));
        return userRepository.save(user);
    }

    // 사용자 이름으로 사용자 찾기 메소드 추가
    public SiteUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
