# implementation - spring-boot-starter-session-jdbc

## Gradle 선언

~~~kotlin
implementation("org.springframework.boot:spring-boot-starter-session-jdbc")
~~~

## 이 의존성이 하는 일

JDBC와 DB 연결을 다루기 위한 의존성이다.

## 언제 쓰는가

해당 기능이 실제 애플리케이션 코드 또는 개발 환경에 필요할 때 쓴다.

## 핵심 개념 더 자세히

JDBC 계열은 자바 애플리케이션과 관계형 DB 사이의 가장 기본적인 연결 방식이다. DataSource가 DB 연결을 관리하고, JdbcTemplate 또는 Repository가 SQL 실행을 담당한다. DB 의존성이 들어오면 스프링부트는 DataSource를 만들려고 하므로 설정이 중요하다.

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

### DB 연결 설정 예시

~~~yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydatabase
    username: myuser
    password: secret
~~~

## 기본 예제 코드

아래 코드는 사용 형태를 보여주는 예시다. 실제 프로젝트에서는 패키지명, 클래스명, 설정 값을 현재 구조에 맞게 바꿔야 한다.

### 예제

~~~yaml
spring:
  session:
    store-type: jdbc
    jdbc:
      initialize-schema: always
~~~

## 추가 예제

### 조회 SQL 예시

~~~java
public List<String> findNames() {
    return jdbcTemplate.query(
            "select name from users",
            (rs, rowNum) -> rs.getString("name")
    );
}
~~~
### 저장 SQL 예시

~~~java
public void insertUser(String name) {
    jdbcTemplate.update("insert into users(name) values (?)", name);
}
~~~

## 주의점

DB 관련 의존성이므로 DataSource 설정이 없으면 실행이나 테스트에서 오류가 날 수 있다.

## 확인 방법

애플리케이션 시작 로그에서 연결 오류가 없는지 보고, 간단한 조회 API나 테스트를 만들어 실제 저장소에 접근되는지 확인한다.

## 자주 생기는 실수

DB 의존성을 추가했는데 spring.datasource.* 설정을 하지 않는 실수가 많다. 또한 Docker의 MySQL 포트와 application.yaml의 포트가 맞는지도 확인해야 한다.

## 같이 보면 좋은 것

- group: org.springframework.boot
- artifact: spring-boot-starter-session-jdbc
- configuration: implementation
- 원본 파일: build.gradle.kts
