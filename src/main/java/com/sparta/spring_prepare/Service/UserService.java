package com.sparta.spring_prepare.Service;

import com.sparta.spring_prepare.Entity.SiteUser;
import com.sparta.spring_prepare.dto.UserCreateForm;
import com.sparta.spring_prepare.Repository.SiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final SiteUserRepository siteUserRepository;
    private final PasswordEncoder passwordEncoder; // PasswordEncoder 주입

    // 회원가입 메서드
    public void register(UserCreateForm userCreateForm) {
        // 비밀번호 해시 처리
        String encodedPassword = passwordEncoder.encode(userCreateForm.getPassword());

        // SiteUser 객체 생성 및 저장
        SiteUser user = new SiteUser();
        user.setUsername(userCreateForm.getUsername());
        user.setPassword(encodedPassword);
        user.setEmail(userCreateForm.getEmail());

        siteUserRepository.save(user);
    }

    // 사용자 이름으로 사용자 검색
    public Optional<SiteUser> findByUsername(String username) {
        return siteUserRepository.findByUsername(username);
    }
}
