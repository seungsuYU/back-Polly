package com.sparta.spring_prepare.Service;

import com.sparta.spring_prepare.Entity.SiteUser;
import com.sparta.spring_prepare.Repository.UserCreateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecirotuService {

    private final UserCreateRepository signUpRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        this.signUpRepository.save(user);
        return user;
    }
}
