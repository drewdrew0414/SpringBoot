# developmentOnly - spring-boot-docker-compose

## Gradle 선언

~~~kotlin
developmentOnly("org.springframework.boot:spring-boot-docker-compose")
~~~

## 이게 뭐하는 건데?

개발 중 Docker Compose 서비스를 스프링부트와 연동하기 위한 의존성이다.

## 언제 쓰면 되냐면

운영 배포에는 넣지 않고 개발 중 편의 기능으로만 사용하고 싶을 때 쓴다.

## 조금 더 풀어서 보면

이 의존성은 특정 기능을 구현할 때 필요한 API, 자동 설정, 런타임 라이브러리를 제공한다. 스프링부트 Starter는 보통 관련 라이브러리 여러 개를 묶어서 가져오므로, 하나를 추가해도 자동 설정 범위가 넓어질 수 있다.

## 동작 흐름

~~~text
의존성 추가
↓
자동 설정 또는 API 사용
↓
코드 작성
↓
실행/테스트
~~~

## 설정은 이런 식으로 함

모든 의존성이 설정을 꼭 요구하는 건 아니지만, 실제 프로젝트에서는 아래처럼 관련 설정이나 사용 방향을 같이 잡는 경우가 많다.

### Docker Compose 연동 설정 예시

~~~yaml
spring:
  docker:
    compose:
      enabled: true
      file: compose.yaml
~~~

## 기본 예제 코드

아래 코드는 이런 식으로 쓰는구나 정도로 보면 된다. 실제 프로젝트에서는 패키지명, 클래스명, 설정 값은 현재 구조에 맞게 바꿔야 한다.

### 예제

~~~yaml
services:
  mysql:
    image: mysql:latest
    environment:
      MYSQL_DATABASE: mydatabase
      MYSQL_USER: myuser
      MYSQL_PASSWORD: secret
      MYSQL_ROOT_PASSWORD: verysecret
~~~

## 추가로 보면 좋은 예시

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

## 주의할 점

예제 코드는 사용 형태를 보여주기 위한 최소 예시이므로 실제 프로젝트 구조에 맞게 수정해야 한다.

## 제대로 되는지 확인하는 법

.\gradlew.bat compileJava로 컴파일을 확인하고, 해당 기능을 쓰는 최소 API나 테스트를 만들어 동작 여부를 검증한다.

## 자주 하는 실수

의존성만 추가하면 기능이 자동으로 완성된다고 생각하기 쉽다. 대부분은 관련 설정, Controller, Service, 테스트 코드가 함께 있어야 실제 기능이 된다.

## 같이 보면 좋은 정보

- group: org.springframework.boot
- artifact: spring-boot-docker-compose
- configuration: developmentOnly
- 원본 파일: build.gradle.kts
