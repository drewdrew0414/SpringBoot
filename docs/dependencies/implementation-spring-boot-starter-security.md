# implementation - spring-boot-starter-security

## Gradle 선언

~~~kotlin
implementation("org.springframework.boot:spring-boot-starter-security")
~~~

## 이 의존성이 하는 일

인증, 인가, 로그인, 토큰, SSO 같은 보안 기능을 위한 의존성이다.

## 언제 쓰는가

해당 기능이 실제 애플리케이션 코드 또는 개발 환경에 필요할 때 쓴다.

## 핵심 개념 더 자세히

Security 계열은 HTTP 요청이 Controller에 도착하기 전에 Filter Chain에서 먼저 동작한다. 인증은 사용자가 누구인지 확인하는 것이고, 인가는 그 사용자가 해당 URL이나 기능에 접근할 수 있는지 판단하는 것이다.

## 기본 동작 흐름

~~~text
의존성 추가
↓
자동 설정 또는 API 사용
↓
코드 작성
↓
실행/테스트
~~~

## 설정 예시

설정이 꼭 필요하지 않은 의존성도 있지만, 실제 프로젝트에서는 아래처럼 관련 설정이나 사용 방향을 함께 잡는 경우가 많다.

### 설정 방향

~~~text
이 의존성은 별도 설정 없이도 자동 설정으로 일부 기능이 켜질 수 있다.
다만 실제 기능을 쓰려면 Controller, Service, Repository, 설정 클래스 중 하나에서 관련 API를 직접 사용해야 한다.
~~~

## 기본 예제 코드

아래 코드는 사용 형태를 보여주는 예시다. 실제 프로젝트에서는 패키지명, 클래스명, 설정 값을 현재 구조에 맞게 바꿔야 한다.

### 예제

~~~java
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/example/**").permitAll()
                    .anyRequest().authenticated())
            .formLogin(form -> form.permitAll())
            .build();
}
~~~

## 추가 예제

### 인증 사용자 조회 예시

~~~java
@GetMapping("/me")
public String me(Authentication authentication) {
    return authentication.getName();
}
~~~
### URL별 권한 규칙 예시

~~~java
.authorizeHttpRequests(auth -> auth
        .requestMatchers("/public/**").permitAll()
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated())
~~~

## 주의점

Security 의존성은 기본 보안을 켤 수 있으므로 허용 경로와 인증 방식을 명확히 설정해야 한다.

## 확인 방법

.\gradlew.bat compileJava로 컴파일을 확인하고, 해당 기능을 쓰는 최소 API나 테스트를 만들어 동작 여부를 검증한다.

## 자주 생기는 실수

Security 의존성을 추가한 뒤 모든 API가 갑자기 401 또는 403으로 막히는 경우가 많다. 공개 API, 로그인 API, Swagger 경로 허용 설정을 따로 잡아야 한다.

## 같이 보면 좋은 것

- group: org.springframework.boot
- artifact: spring-boot-starter-security
- configuration: implementation
- 원본 파일: build.gradle.kts
