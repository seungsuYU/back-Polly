package com.sparta.spring_prepare.api;

import com.sparta.spring_prepare.Service.UserService;
import com.sparta.spring_prepare.dto.UserCreateForm;
import com.sparta.spring_prepare.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserCreateForm userCreateForm) {
        userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCreateForm userCreateForm) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCreateForm.getUsername(), userCreateForm.getPassword1())
        );
        final String token = jwtUtil.generateToken(userCreateForm.getUsername());
        return ResponseEntity.ok(token);
    }
}
