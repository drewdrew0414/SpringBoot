# implementation - spring-boot-starter-webflux

## Gradle 선언

~~~kotlin
implementation("org.springframework.boot:spring-boot-starter-webflux")
~~~

## 이 의존성이 하는 일

비동기/논블로킹 방식의 Spring WebFlux API를 만들기 위한 의존성이다.

## 언제 쓰는가

해당 기능이 실제 애플리케이션 코드 또는 개발 환경에 필요할 때 쓴다.

## 핵심 개념 더 자세히

WebFlux 계열은 reactive 방식이다. Mono는 0개 또는 1개의 값을, Flux는 0개 이상의 값 흐름을 표현한다. 많은 동시 연결이나 외부 API 호출이 많은 시스템에서 장점이 있지만, MVC보다 사고방식이 다르기 때문에 초반에는 예제를 작게 나눠서 보는 것이 좋다.

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
import reactor.core.publisher.Mono;

@RestController
public class ReactiveHelloController {
    @GetMapping("/reactive/hello")
    public Mono<String> hello() {
        return Mono.just("hello webflux");
    }
}
~~~

## 추가 예제

### Service 계층 사용 예시

~~~java
@Service
public class ExampleService {
    public String execute() {
        return "dependency usage example";
    }
}
~~~
### Controller 연결 예시

~~~java
@RestController
public class ExampleController {
    @GetMapping("/example")
    public String example() {
        return "ok";
    }
}
~~~

## 주의점

Reactive 방식은 MVC 방식과 사고방식이 다르므로 처음에는 작은 예제로 분리해서 학습하는 편이 좋다.

## 확인 방법

애플리케이션을 실행한 뒤 브라우저, Postman, PowerShell Invoke-RestMethod로 endpoint를 호출해서 상태 코드와 응답 Body를 확인한다.

## 자주 생기는 실수

Reactive 타입을 반환하면서 중간에 lock()을 남발하면 WebFlux를 쓰는 장점이 줄어든다. MVC와 reactive 코드를 섞을 때는 경계를 명확히 해야 한다.

## 같이 보면 좋은 것

- group: org.springframework.boot
- artifact: spring-boot-starter-webflux
- configuration: implementation
- 원본 파일: build.gradle.kts
