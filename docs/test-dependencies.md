# 테스트 의존성

이 문서는 `build.gradle.kts`에 들어 있는 테스트 관련 의존성을 설명하는 문서다.

포함되는 범위는 아래와 같음.

```
testImplementation
testCompileOnly
testRuntimeOnly
testAnnotationProcessor
```

테스트 의존성은 애플리케이션 main 코드 실행에 직접 들어가는 것이 아니라, 테스트 코드를 컴파일하고 실행할 때 사용된다.

## 1. `org.springframework.boot:spring-boot-starter-data-jdbc-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-data-jdbc-test")
```

Spring Data JDBC 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
Spring Data JDBC Repository 테스트
↓
JDBC 기반 데이터 접근 테스트
↓
DB 관련 테스트 환경 구성 보조
```

main의 `spring-boot-starter-data-jdbc`와 짝을 이루는 테스트 의존성이다.

## 2. `org.springframework.boot:spring-boot-starter-data-ldap-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-data-ldap-test")
```

Spring Data LDAP 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
LDAP Repository 테스트
↓
LDAP 데이터 접근 코드 검증
↓
LDAP 연동 테스트 보조
```

LDAP 기능을 실제로 사용하지 않는다면 필요성이 낮다.

## 3. `org.springframework.boot:spring-boot-starter-data-rest-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-data-rest-test")
```

Spring Data REST 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
Repository 기반 REST endpoint 테스트
↓
HAL 응답 테스트
↓
Spring Data REST 자동 노출 API 검증
```

Controller를 직접 작성하는 일반 REST API 테스트와는 목적이 다르다.

## 4. `org.springframework.boot:spring-boot-starter-graphql-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-graphql-test")
```

Spring GraphQL 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
GraphQL Query 테스트
↓
GraphQL Mutation 테스트
↓
GraphQL 응답 필드 검증
```

Spring for GraphQL을 사용할 때 의미가 있다.

## 5. `org.springframework.boot:spring-boot-starter-hateoas-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-hateoas-test")
```

Spring HATEOAS 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
_links 포함 응답 검증
↓
HAL 응답 구조 검증
↓
RepresentationModel 기반 응답 테스트
```

HATEOAS 응답을 만들지 않는다면 필요성이 낮다.

## 6. `org.springframework.boot:spring-boot-starter-jdbc-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-jdbc-test")
```

JDBC 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
JdbcTemplate 테스트
↓
SQL 실행 결과 검증
↓
DB 연결 테스트
```

JDBC 관련 테스트는 DataSource가 필요할 수 있다.

## 7. `org.springframework.boot:spring-boot-starter-jersey-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-jersey-test")
```

Jersey 기반 API 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
JAX-RS resource 테스트
↓
Jersey endpoint 테스트
↓
Spring MVC가 아닌 Jersey API 검증
```

현재 예시 코드는 Spring MVC Controller를 사용하므로, 당장 필요하지 않을 수 있다.

## 8. `org.springframework.boot:spring-boot-starter-ldap-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-ldap-test")
```

LDAP 연동 테스트를 위한 Starter다.

`data-ldap-test`와 차이:

```
ldap-test
→ 기본 LDAP 연동 테스트

data-ldap-test
→ Spring Data LDAP Repository 테스트
```

LDAP 서버 또는 테스트용 LDAP 환경이 필요할 수 있다.

## 9. `org.springframework.boot:spring-boot-starter-restclient-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-restclient-test")
```

`RestClient` 기반 외부 HTTP 호출 코드를 테스트하기 위한 Starter다.

사용 목적은 아래와 같음.

```
외부 API 호출 코드 테스트
↓
HTTP 요청/응답 mocking
↓
RestClient 설정 검증
```

다른 서버를 호출하는 코드가 있을 때 유용하다.

## 10. `org.springframework.boot:spring-boot-starter-security-oauth2-authorization-server-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-security-oauth2-authorization-server-test")
```

OAuth2 Authorization Server 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
토큰 발급 흐름 테스트
↓
Authorization Code 흐름 테스트
↓
OAuth2 서버 설정 검증
```

Authorization Server 기능을 직접 만들 때 필요한 고급 테스트 의존성이다.

## 11. `org.springframework.boot:spring-boot-starter-security-oauth2-client-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-security-oauth2-client-test")
```

OAuth2 Client 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
소셜 로그인 흐름 테스트
↓
OAuth2 로그인 사용자 mocking
↓
OAuth2 client 설정 검증
```

Google, Kakao, Naver, GitHub 로그인 같은 기능을 테스트할 때 의미가 있다.

## 12. `org.springframework.boot:spring-boot-starter-security-oauth2-resource-server-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-security-oauth2-resource-server-test")
```

OAuth2 Resource Server 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
JWT 인증 API 테스트
↓
Access Token 검증 흐름 테스트
↓
인증된 사용자 요청 mocking
```

토큰 기반 API 서버 테스트에서 사용한다.

## 13. `org.springframework.boot:spring-boot-starter-security-saml2-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-security-saml2-test")
```

SAML2 인증 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
SAML2 로그인 테스트
↓
SSO 연동 테스트
↓
SAML 응답 처리 검증
```

기업용 SSO를 직접 연동할 때 의미가 있다.

## 14. `org.springframework.boot:spring-boot-starter-security-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-security-test")
```

Spring Security 테스트를 위한 기본 Starter다.

자주 쓰는 기능:

```
인증 사용자 mocking
↓
권한 테스트
↓
CSRF 테스트
↓
보안 설정 검증
```

예시:

```java
@WithMockUser(username = "kim", roles = "USER")
@Test
void userApiTest() {
}
```

Spring Security가 들어간 프로젝트에서는 유용하다.

## 15. `org.springframework.boot:spring-boot-starter-session-data-redis-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-session-data-redis-test")
```

Redis 기반 Spring Session 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
Redis 세션 저장 테스트
↓
세션 공유 테스트
↓
로그인 세션 동작 검증
```

Redis 테스트 환경이 필요할 수 있다.

## 16. `org.springframework.boot:spring-boot-starter-session-jdbc-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-session-jdbc-test")
```

JDBC 기반 Spring Session 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
DB 세션 저장 테스트
↓
세션 테이블 사용 검증
↓
로그인 세션 유지 테스트
```

DataSource 설정이 필요할 수 있다.

## 17. `org.springframework.boot:spring-boot-starter-webclient-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-webclient-test")
```

`WebClient` 기반 외부 HTTP 호출 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
비동기 외부 API 호출 테스트
↓
WebClient 요청/응답 검증
↓
Mono, Flux 기반 응답 테스트
```

WebFlux나 reactive client 코드를 사용할 때 의미가 있다.

## 18. `org.springframework.boot:spring-boot-starter-webflux-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-webflux-test")
```

Spring WebFlux 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
WebFlux Controller 테스트
↓
RouterFunction 테스트
↓
WebTestClient 사용
↓
Mono, Flux 응답 검증
```

WebFlux 기반 API를 만들 때 사용한다.

## 19. `org.springframework.boot:spring-boot-starter-webmvc-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
```

Spring MVC Controller 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
@RestController 테스트
↓
MockMvc 테스트
↓
요청/응답 JSON 검증
↓
HTTP 상태 코드 검증
```

현재 예시 Controller를 테스트한다면 가장 직접적으로 관련 있는 테스트 의존성이다.

예시:

```java
mockMvc.perform(get("/example/hello"))
        .andExpect(status().isOk())
        .andExpect(content().string("hello spring"));
```

## 20. `org.springframework.boot:spring-boot-starter-webservices-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-webservices-test")
```

Spring Web Services 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
SOAP endpoint 테스트
↓
XML 요청/응답 검증
↓
WebServiceTemplate 테스트
```

SOAP 웹 서비스를 만들거나 연동할 때 사용한다.

## 21. `org.springframework.boot:spring-boot-starter-websocket-test`

```kotlin
testImplementation("org.springframework.boot:spring-boot-starter-websocket-test")
```

WebSocket 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
WebSocket 연결 테스트
↓
메시지 송수신 테스트
↓
STOMP endpoint 테스트
```

채팅, 실시간 알림 같은 기능을 테스트할 때 사용한다.

## 22. `com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter-test`

```kotlin
testImplementation("com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter-test")
```

Netflix DGS GraphQL 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
DGS DataFetcher 테스트
↓
GraphQL query 실행 테스트
↓
DGS schema 기반 응답 검증
```

main의 `graphql-dgs-spring-graphql-starter`와 짝을 이룬다.

## 23. `org.springframework.modulith:spring-modulith-starter-test`

```kotlin
testImplementation("org.springframework.modulith:spring-modulith-starter-test")
```

Spring Modulith 테스트를 위한 Starter다.

사용 목적은 아래와 같음.

```
모듈 구조 검증
↓
모듈 간 의존성 규칙 테스트
↓
애플리케이션 이벤트 테스트
```

Modulith를 쓰는 가장 큰 이유 중 하나가 모듈 경계를 테스트로 검증하는 것이다.

## 24. `org.projectlombok:lombok`

```kotlin
testCompileOnly("org.projectlombok:lombok")
```

테스트 코드에서 Lombok 어노테이션을 사용할 수 있게 해준다.

main 코드의 `compileOnly("org.projectlombok:lombok")`와 같은 역할을 테스트 코드에 적용한 것이다.

사용 예:

```java
@Getter
class TestFixture {
    private String name;
}
```

테스트 코드에서 Lombok을 쓰지 않는다면 필수는 아니다.

## 25. `org.junit.platform:junit-platform-launcher`

```kotlin
testRuntimeOnly("org.junit.platform:junit-platform-launcher")
```

JUnit Platform 테스트 실행 런처다.

`testRuntimeOnly`인 이유:

```
테스트 코드를 컴파일할 때 직접 사용하지 않음
↓
테스트 실행 시점에 JUnit Platform이 필요
```

Gradle, IDE, JUnit 5 테스트 실행과 관련이 있다.

현재 `build.gradle.kts`에는 아래 설정도 있다.

```kotlin
tasks.withType<Test> {
    useJUnitPlatform()
}
```

이 설정과 함께 JUnit 5 테스트 실행을 지원한다.

## 26. `org.projectlombok:lombok`

```kotlin
testAnnotationProcessor("org.projectlombok:lombok")
```

테스트 코드에서 Lombok 어노테이션을 실제 코드로 생성하기 위한 annotation processor다.

테스트 코드에서 Lombok을 쓰려면 보통 아래 두 줄이 같이 필요하다.

```kotlin
testCompileOnly("org.projectlombok:lombok")
testAnnotationProcessor("org.projectlombok:lombok")
```

흐름:

```
테스트 코드의 @Getter 발견
↓
Lombok testAnnotationProcessor 실행
↓
getter 코드 생성
↓
테스트 코드 컴파일
```

## 테스트 의존성 정리

현재 프로젝트는 main 의존성이 많은 만큼 테스트 의존성도 매우 많다.

실제 프로젝트라면 아래처럼 필요한 테스트 의존성만 남기는 편이 좋다.

```
Spring MVC API만 테스트
→ spring-boot-starter-webmvc-test
→ spring-boot-starter-security-test

JDBC Repository 테스트
→ spring-boot-starter-jdbc-test
→ spring-boot-starter-data-jdbc-test

GraphQL DGS 테스트
→ graphql-dgs-spring-graphql-starter-test

Modulith 테스트
→ spring-modulith-starter-test
```

너무 많은 테스트 Starter는 자동 설정 범위를 넓혀서 테스트 컨텍스트가 무거워질 수 있다.
