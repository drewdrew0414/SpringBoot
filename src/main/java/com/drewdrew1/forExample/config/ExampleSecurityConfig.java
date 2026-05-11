package com.drewdrew1.forExample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
 * 이 프로젝트에는 spring-boot-starter-security 의존성이 들어 있다.
 *
 * Spring Security가 있으면 기본적으로 모든 API가 로그인 보호를 받을 수 있다.
 * 그러면 학습용 예시 API를 브라우저나 PowerShell에서 바로 호출하기 어렵다.
 *
 * 그래서 /example/** 주소만 로그인 없이 접근할 수 있게 열어둔다.
 * 실제 서비스에서는 보안 정책을 훨씬 더 신중하게 설계해야 한다.
 */
@Configuration
public class ExampleSecurityConfig {

    /*
     * SecurityFilterChain은 HTTP 요청에 어떤 보안 규칙을 적용할지 정하는 Bean이다.
     *
     * 여기서는 학습 편의를 위해
     * - /example/** 요청은 모두 허용
     * - 그 외 요청은 인증 필요
     * 로 설정한다.
     */
    @Bean
    public SecurityFilterChain exampleSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                /*
                 * CSRF 보호는 브라우저 기반 로그인 서비스에서 중요한 보호 장치다.
                 *
                 * 다만 이 예제에서는 PowerShell/Postman으로 POST 요청을 쉽게 보내는 것이 목적이라
                 * 학습용 API에 한해 CSRF를 꺼둔다.
                 */
                .csrf(csrf -> csrf.disable())
                /*
                 * URL별 접근 규칙이다.
                 *
                 * /example/** 은 예시 API 경로 전체를 의미한다.
                 */
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/example/**").permitAll()
                        .anyRequest().authenticated()
                )
                /*
                 * 기본 로그인 폼을 유지한다.
                 * /example/** 이외의 보호된 페이지에 접근할 때 사용할 수 있다.
                 */
                .formLogin(Customizer.withDefaults())
                /*
                 * HTTP Basic 인증도 기본값으로 켜둔다.
                 * API 테스트 도구에서 인증을 넣어 호출할 때 사용할 수 있다.
                 */
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
