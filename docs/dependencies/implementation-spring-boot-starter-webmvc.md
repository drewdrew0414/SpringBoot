# implementation - spring-boot-starter-webmvc

## Gradle 선언

~~~kotlin
implementation("org.springframework.boot:spring-boot-starter-webmvc")
~~~

## 이 의존성이 하는 일

Spring MVC 기반 REST API를 만들기 위한 기본 웹 의존성이다.

## 언제 쓰는가

해당 기능이 실제 애플리케이션 코드 또는 개발 환경에 필요할 때 쓴다.

## 핵심 개념 더 자세히

Spring MVC는 요청 1개를 스레드 1개가 처리하는 전통적인 Servlet 기반 웹 방식이다. @RestController, @GetMapping, @RequestBody 같은 어노테이션을 사용해서 HTTP 요청을 자바 메서드에 연결한다. 일반적인 REST API 학습과 실무 백엔드에서 가장 많이 만나는 방식이다.

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

### 웹 서버 기본 설정 예시

~~~yaml
server:
  port: 8080

spring:
  application:
    name: SpringBoot
~~~

## 기본 예제 코드

아래 코드는 사용 형태를 보여주는 예시다. 실제 프로젝트에서는 패키지명, 클래스명, 설정 값을 현재 구조에 맞게 바꿔야 한다.

### 예제

~~~java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello spring mvc";
    }
}
~~~

## 추가 예제

### PathVariable 사용 예시

~~~java
@GetMapping("/users/{id}")
public UserResponse findUser(@PathVariable Long id) {
    return userService.findUser(id);
}
~~~
### RequestBody + 201 응답 예시

~~~java
@PostMapping("/users")
@ResponseStatus(HttpStatus.CREATED)
public UserResponse createUser(@RequestBody UserCreateRequest request) {
    return userService.createUser(request);
}
~~~

## 주의점

예제 코드는 사용 형태를 보여주기 위한 최소 예시이므로 실제 프로젝트 구조에 맞게 수정해야 한다.

## 확인 방법

애플리케이션을 실행한 뒤 브라우저, Postman, PowerShell Invoke-RestMethod로 endpoint를 호출해서 상태 코드와 응답 Body를 확인한다.

## 자주 생기는 실수

의존성만 추가하면 기능이 자동으로 완성된다고 생각하기 쉽다. 대부분은 관련 설정, Controller, Service, 테스트 코드가 함께 있어야 실제 기능이 된다.

## 같이 보면 좋은 것

- group: org.springframework.boot
- artifact: spring-boot-starter-webmvc
- configuration: implementation
- 원본 파일: build.gradle.kts
