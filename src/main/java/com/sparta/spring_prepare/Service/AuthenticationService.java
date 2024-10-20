package com.sparta.spring_prepare.Service;

import com.sparta.spring_prepare.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    public String authenticate(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return jwtUtil.generateToken(userDetails.getUsername());
        } catch (BadCredentialsException e) {
            System.out.println("인증 실패: " + e.getMessage()); // 인증 실패 로그
            throw new RuntimeException("인증 실패: 사용자 이름 또는 비밀번호가 잘못되었습니다.");
        } catch (Exception e) {
            System.out.println("기타 예외 발생: " + e.getMessage()); // 기타 예외 로그
            throw new RuntimeException("인증 중 오류가 발생했습니다.");
        }
    }

}
