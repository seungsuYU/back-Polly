package com.sparta.spring_prepare.CORS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // 실제 허용할 도메인으로 수정
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true);

                registry.addMapping("/myHandler") // 채팅 관련 요청 허용
                        .allowedOrigins("http://localhost:3000") // 채팅 요청에도 동일한 도메인 허용
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true);
            }
        };
    }

}
