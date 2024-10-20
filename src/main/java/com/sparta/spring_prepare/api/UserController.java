package com.sparta.spring_prepare.api;

import com.sparta.spring_prepare.Service.UserService;
import com.sparta.spring_prepare.dto.LoginRequest;
import com.sparta.spring_prepare.dto.UserCreateForm;
import com.sparta.spring_prepare.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserCreateForm userCreateForm) {
        userService.register(userCreateForm);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("입력된 사용자 이름: " + loginRequest.getUsername());
        System.out.println("입력된 비밀번호: " + loginRequest.getPassword());

        // 추가된 로그: userCreateForm의 모든 필드 출력
        System.out.println("요청 본문: " + loginRequest);

        try {
            // loginRequest에서 사용자 이름과 비밀번호 가져오기
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            final String token = jwtUtil.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("잘못된 사용자 이름 또는 비밀번호입니다.");
        }
    }
}
