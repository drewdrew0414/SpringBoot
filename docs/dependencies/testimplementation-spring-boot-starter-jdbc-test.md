# testImplementation - spring-boot-starter-jdbc-test

## Gradle 선언

~~~kotlin
testImplementation("org.springframework.boot:spring-boot-starter-jdbc-test")
~~~

## 이게 뭐하는 건데?

이 의존성은 spring-boot-starter-jdbc-test 기능을 테스트 코드에서 검증하기 위한 테스트 전용 의존성이다.

## 언제 쓰면 되냐면

해당 기능을 사용하는 코드가 제대로 동작하는지 테스트에서 검증하고 싶을 때 쓴다.

## 조금 더 풀어서 보면

테스트 의존성은 운영 코드가 아니라 테스트 코드에서 쓰인다. Controller 테스트, Repository 테스트, Security 테스트처럼 특정 계층이나 기능을 검증할 때 필요한 테스트 도구와 자동 설정을 제공한다.

이 의존성은 실제 기능 구현을 대신하지 않는다. main 코드에 같은 계열 기능이 있어야 테스트 의존성도 의미가 있다. 예를 들어 WebMVC 테스트 의존성은 Controller를 테스트하기 위한 것이지 Controller 기능 자체를 제공하는 것은 아니다.

## 동작 흐름

~~~text
테스트 의존성 추가
↓
테스트 컨텍스트 구성
↓
기능 실행
↓
결과 검증
~~~

## 설정은 이런 식으로 함

모든 의존성이 설정을 꼭 요구하는 건 아니지만, 실제 프로젝트에서는 아래처럼 관련 설정이나 사용 방향을 같이 잡는 경우가 많다.

### DB 연결 설정 예시

~~~yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydatabase
    username: myuser
    password: secret
~~~

## 기본 예제 코드

아래 코드는 이런 식으로 쓰는구나 정도로 보면 된다. 실제 프로젝트에서는 패키지명, 클래스명, 설정 값은 현재 구조에 맞게 바꿔야 한다.

### 예제

~~~java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class JdbcExampleTest {
    @Autowired JdbcTemplate jdbcTemplate;

    @Test
    void query() {
        jdbcTemplate.queryForObject("select 1", Integer.class);
    }
}
~~~

## 추가로 보면 좋은 예시

### 단위 테스트 스타일 예시

~~~java
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimpleUnitTest {
    @Test
    @DisplayName("기본 단위 테스트 예시")
    void simpleTest() {
        // given
        int a = 1;
        int b = 2;

        // when
        int result = a + b;

        // then
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(3);
    }
}
~~~
### 테스트 실행 명령어

~~~powershell
.\gradlew.bat test
~~~

## 주의할 점

DB 관련 의존성이므로 DataSource 설정이 없으면 실행이나 테스트에서 오류가 날 수 있다.

## 제대로 되는지 확인하는 법

테스트 클래스를 작성한 뒤 .\gradlew.bat test를 실행한다. 테스트가 실패하면 report 경로의 HTML 리포트에서 실패 원인을 확인한다.

## 자주 하는 실수

테스트 의존성만 추가하고 실제 테스트 대상 Bean이나 설정을 준비하지 않으면 테스트가 의미 있게 검증되지 않는다. 어떤 계층을 테스트할지 먼저 정해야 한다.

## 같이 보면 좋은 정보

- group: org.springframework.boot
- artifact: spring-boot-starter-jdbc-test
- configuration: testImplementation
- 원본 파일: build.gradle.kts
