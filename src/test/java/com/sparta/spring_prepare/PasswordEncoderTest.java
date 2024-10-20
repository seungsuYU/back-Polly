package com.sparta.spring_prepare;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordEncoderTest {
    @Test
    public void testPasswordMatching() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "aaaaaaaaaa"; // 입력한 비밀번호를 실제 비밀번호로 변경
        String hashedPassword = "$2a$10$t9hkvfzsx5zgHE7t8FdB1etBF7OBJmDLxakV4jppLmwnlaOPROctO"; // DB에서 가져온 해시 비밀번호

        boolean matches = passwordEncoder.matches(rawPassword, hashedPassword);
        assertTrue(matches, "비밀번호가 일치하지 않습니다."); // 비밀번호가 일치하지 않을 경우 테스트 실패
    }
}
