package com.sparta.spring_prepare.api;

import com.sparta.spring_prepare.Entity.SiteUser;
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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<UserCreateForm> signup(@RequestBody UserCreateForm userCreateForm) {
        userService.register(userCreateForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreateForm);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // 사용자 인증
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            // 사용자 정보 가져오기
            SiteUser user = userService.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("사용자를 찾을 수 없습니다."));

            // JWT 토큰 생성
            final String token = jwtUtil.generateToken(user.getUsername());

            // 사용자 정보와 토큰을 함께 반환
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("username", user.getUsername());
            response.put("email", user.getEmail());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("잘못된 사용자 이름 또는 비밀번호입니다.");
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<SiteUser> getUser(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
