# main 구현 의존성

이 문서는 `build.gradle.kts`의 `implementation(...)` 의존성을 하나씩 설명한다.

`implementation`은 애플리케이션의 main 코드에서 직접 사용하거나, 스프링부트 자동 설정에 의해 실행 시점에 필요한 의존성이다.

## 1. `org.springframework.boot:spring-boot-starter-data-jdbc`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
```

Spring Data JDBC를 사용하기 위한 Starter다.

하는 일은 아래와 같음.

```
JDBC 기반 Repository 지원
↓
DataSource 사용
↓
간단한 객체-테이블 매핑
↓
Spring Data Repository 패턴 지원
```

JPA보다 단순한 방식으로 DB를 다루고 싶을 때 사용한다.

주의할 점:

```
DB 연결 정보 필요
↓
spring.datasource.url
spring.datasource.username
spring.datasource.password
```

현재 프로젝트에서 DB 설정 없이 테스트가 실패할 수 있는 원인 중 하나다.

## 2. `org.springframework.boot:spring-boot-starter-data-ldap`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-data-ldap")
```

Spring Data LDAP를 사용하기 위한 Starter다.

LDAP는 계정, 조직, 인증 정보 같은 디렉터리 데이터를 저장하고 조회하는 시스템이다.

사용 예:

```
회사 내부 사용자 디렉터리 조회
↓
LDAP 서버에서 사용자 정보 검색
↓
조직도, 권한, 계정 정보 연동
```

일반 게시판, 쇼핑몰, 간단한 REST API에서는 보통 필요하지 않다.

## 3. `org.springframework.boot:spring-boot-starter-data-rest`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-data-rest")
```

Spring Data Repository를 REST API로 자동 노출하는 Starter다.

예를 들어 Repository를 만들면, 별도 Controller 없이도 REST endpoint가 생길 수 있다.

장점:

```
간단한 CRUD API 빠르게 생성
↓
Repository 기반 REST endpoint 자동 제공
↓
HAL 형식 응답 지원
```

주의할 점:

```
원하지 않는 Repository가 외부에 노출될 수 있음
↓
API 설계가 Repository 구조에 묶일 수 있음
↓
실무에서는 노출 범위와 보안을 신중히 봐야 함
```

학습용으로는 Controller를 직접 만들어보는 것이 구조 이해에 더 좋다.

## 4. `org.springframework.boot:spring-boot-starter-graphql`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-graphql")
```

Spring for GraphQL을 사용하기 위한 Starter다.

GraphQL은 REST처럼 여러 URL을 만드는 방식이 아니라, 하나의 GraphQL endpoint에 query를 보내 필요한 데이터 형태를 요청하는 방식이다.

REST와 비교:

```
REST
→ GET /users
→ GET /users/1/posts

GraphQL
→ POST /graphql
→ 원하는 필드만 query로 요청
```

주의할 점:

현재 프로젝트에는 Netflix DGS GraphQL starter도 같이 들어 있다.
둘 다 GraphQL 관련 기능을 제공하므로 실제 프로젝트에서는 어느 GraphQL 방식을 중심으로 쓸지 정하는 것이 좋다.

## 5. `org.springframework.boot:spring-boot-starter-hateoas`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-hateoas")
```

HATEOAS를 지원하는 Starter다.

HATEOAS는 응답 데이터에 관련 API 링크를 함께 담는 방식이다.

예시:

```json
{
  "id": 1,
  "name": "kim",
  "_links": {
    "self": {
      "href": "/users/1"
    },
    "orders": {
      "href": "/users/1/orders"
    }
  }
}
```

장점:

```
클라이언트가 다음 행동 가능한 링크를 응답에서 확인 가능
↓
REST API 표현력이 좋아짐
```

일반적인 초급 REST API에서는 필수는 아니다.

## 6. `org.springframework.boot:spring-boot-starter-jdbc`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-jdbc")
```

JDBC를 직접 사용하기 위한 Starter다.

포함되는 대표 기능:

```
JdbcTemplate
↓
DataSource 자동 설정
↓
트랜잭션 관리
```

Spring Data JDBC와 차이:

```
spring-boot-starter-jdbc
→ SQL을 직접 작성하는 JdbcTemplate 중심

spring-boot-starter-data-jdbc
→ Spring Data Repository 패턴까지 제공
```

DB 설정이 필요하므로, DataSource 설정이 없으면 실행 시 오류가 날 수 있다.

## 7. `org.springframework.boot:spring-boot-starter-jersey`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-jersey")
```

Jersey 기반 JAX-RS API를 만들기 위한 Starter다.

일반적인 스프링 웹 API는 Spring MVC를 사용한다.
Jersey는 자바 표준 REST API 스타일인 JAX-RS를 구현한 프레임워크다.

비교:

```
Spring MVC
→ @RestController, @GetMapping 사용

Jersey/JAX-RS
→ @Path, @GET 같은 어노테이션 사용
```

현재 프로젝트에는 `spring-boot-starter-webmvc`도 들어 있다.
처음 배우는 단계라면 Spring MVC 하나로 시작하는 편이 이해하기 쉽다.

## 8. `org.springframework.boot:spring-boot-starter-ldap`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-ldap")
```

LDAP 서버와 연동하기 위한 기본 Starter다.

`spring-boot-starter-data-ldap`와 차이:

```
spring-boot-starter-ldap
→ LDAP 연결과 기본 작업 지원

spring-boot-starter-data-ldap
→ Spring Data Repository 스타일의 LDAP 접근 지원
```

회사 계정 시스템이나 LDAP 인증 서버를 연동할 때 사용한다.

일반적인 REST API 학습에는 없어도 된다.

## 9. `org.springframework.boot:spring-boot-starter-restclient`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-restclient")
```

다른 서버의 HTTP API를 호출하는 동기식 REST client를 사용하기 위한 Starter다.

주로 `RestClient`를 사용할 때 필요하다.

예시 상황:

```
우리 서버
↓
외부 결제 API 호출
↓
외부 날씨 API 호출
↓
다른 마이크로서비스 API 호출
```

서버가 요청을 받는 기능이 아니라, 서버가 다른 서버로 요청을 보내는 기능이다.

## 10. `org.springframework.boot:spring-boot-starter-security`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-security")
```

Spring Security를 사용하기 위한 기본 Starter다.

하는 일은 아래와 같음.

```
인증
→ 사용자가 누구인지 확인

인가
→ 이 사용자가 접근 가능한지 확인

보안 Filter Chain 구성
→ HTTP 요청 앞단에서 보안 검사
```

주의할 점:

이 의존성을 추가하면 기본적으로 모든 요청이 보호될 수 있다.
그래서 별도 Security 설정이 없으면 브라우저에서 로그인 페이지가 뜨거나 API가 401/403으로 막힐 수 있다.

현재 프로젝트에는 학습용으로 `/example/**` 경로를 열어둔 `ExampleSecurityConfig`가 있다.

## 11. `org.springframework.boot:spring-boot-starter-security-oauth2-authorization-server`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-security-oauth2-authorization-server")
```

OAuth2 Authorization Server를 만들기 위한 Starter다.

Authorization Server는 토큰을 발급하는 서버다.

역할:

```
사용자 로그인 처리
↓
Authorization Code 발급
↓
Access Token 발급
↓
Refresh Token 발급
```

예시:

```
우리 서비스가 자체 OAuth2 로그인 서버 역할을 함
```

일반 API 서버를 만드는 단계에서는 난이도가 높은 의존성이다.

## 12. `org.springframework.boot:spring-boot-starter-security-oauth2-client`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-security-oauth2-client")
```

OAuth2 Client 기능을 사용하기 위한 Starter다.

OAuth2 Client는 다른 인증 제공자에게 로그인을 맡기는 쪽이다.

예시:

```
Google 로그인
↓
Kakao 로그인
↓
Naver 로그인
↓
GitHub 로그인
```

Authorization Server와 차이:

```
Authorization Server
→ 토큰을 발급하는 서버

OAuth2 Client
→ 다른 서버가 발급한 토큰을 받아 로그인하는 애플리케이션
```

소셜 로그인을 구현할 때 자주 사용한다.

## 13. `org.springframework.boot:spring-boot-starter-security-oauth2-resource-server`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-security-oauth2-resource-server")
```

OAuth2 Resource Server를 만들기 위한 Starter다.

Resource Server는 Access Token을 검사하고 보호된 API를 제공하는 서버다.

흐름:

```
클라이언트가 Access Token을 들고 API 요청
↓
Resource Server가 토큰 검증
↓
토큰이 유효하면 API 응답
↓
토큰이 없거나 틀리면 401
```

JWT 기반 API 서버에서 많이 사용된다.

## 14. `org.springframework.boot:spring-boot-starter-security-saml2`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-security-saml2")
```

SAML2 로그인을 지원하는 Starter다.

SAML2는 기업용 SSO에서 자주 사용하는 인증 프로토콜이다.

예시:

```
회사 계정으로 여러 사내 서비스 로그인
↓
외부 IdP와 연동
↓
엔터프라이즈 SSO 구성
```

일반적인 개인 프로젝트나 초급 API 학습에서는 보통 필요하지 않다.

## 15. `org.springframework.boot:spring-boot-starter-session-data-redis`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-session-data-redis")
```

Spring Session을 Redis에 저장하기 위한 Starter다.

기본 세션은 서버 메모리에 저장될 수 있다.
서버가 여러 대면 세션을 공유하기 어렵다.

Redis 세션을 사용하면:

```
서버 A
↓
Redis에 세션 저장
↑
서버 B도 같은 세션 조회 가능
```

로그인 세션을 여러 서버에서 공유해야 할 때 사용한다.

Redis 서버 설정이 필요하다.

## 16. `org.springframework.boot:spring-boot-starter-session-jdbc`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-session-jdbc")
```

Spring Session을 JDBC DB에 저장하기 위한 Starter다.

세션 정보를 DB 테이블에 저장한다.

사용 상황:

```
로그인 세션을 서버 메모리가 아니라 DB에 저장
↓
서버 재시작 후에도 세션 유지 가능
↓
여러 서버에서 세션 공유 가능
```

주의할 점:

DB 연결과 세션 테이블 설정이 필요할 수 있다.
이 의존성도 DataSource 자동 설정과 연결되어 실행 실패 원인이 될 수 있다.

## 17. `org.springframework.boot:spring-boot-starter-webclient`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-webclient")
```

`WebClient`를 사용하기 위한 Starter다.

`WebClient`는 다른 서버의 HTTP API를 호출하는 클라이언트다.
비동기/논블로킹 방식에 잘 맞는다.

예시:

```
외부 API 호출
↓
결과를 Mono 또는 Flux로 받음
↓
비동기 흐름 처리
```

`RestClient`와 비교:

```
RestClient
→ 동기식 HTTP client

WebClient
→ 비동기/논블로킹 HTTP client
```

## 18. `org.springframework.boot:spring-boot-starter-webflux`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-webflux")
```

Spring WebFlux 기반 웹 서버를 만들기 위한 Starter다.

WebFlux는 비동기/논블로킹 웹 프레임워크다.

특징:

```
Mono, Flux 사용
↓
논블로킹 I/O
↓
많은 동시 요청 처리에 유리할 수 있음
```

주의할 점:

현재 프로젝트에는 `webmvc`도 같이 들어 있다.
초급 단계에서는 Spring MVC와 WebFlux를 동시에 배우면 혼란스러울 수 있다.

일반적인 REST API 학습은 `spring-boot-starter-webmvc`부터 시작하는 것이 좋다.

## 19. `org.springframework.boot:spring-boot-starter-webmvc`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-webmvc")
```

Spring MVC 기반 웹 API를 만들기 위한 Starter다.

이 프로젝트의 예시 Controller가 사용하는 방식이다.

제공 기능:

```
@RestController
↓
@GetMapping, @PostMapping
↓
DispatcherServlet
↓
JSON 변환
↓
내장 Tomcat 기반 HTTP 서버
```

스프링부트를 처음 배울 때 가장 기본이 되는 웹 의존성이다.

## 20. `org.springframework.boot:spring-boot-starter-webservices`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-webservices")
```

Spring Web Services를 사용하기 위한 Starter다.

주로 SOAP 기반 웹 서비스를 만들거나 연동할 때 사용한다.

REST와 비교:

```
REST
→ JSON 중심, URL과 HTTP Method 사용

SOAP
→ XML 중심, WSDL 기반 계약 사용
```

요즘 일반 웹 API는 REST나 GraphQL을 많이 쓰지만, 금융/공공/기업 시스템에서는 SOAP 연동이 남아 있는 경우가 있다.

## 21. `org.springframework.boot:spring-boot-starter-websocket`

```kotlin
implementation("org.springframework.boot:spring-boot-starter-websocket")
```

WebSocket 기능을 사용하기 위한 Starter다.

WebSocket은 클라이언트와 서버가 연결을 유지하면서 양방향으로 데이터를 주고받는 기술이다.

사용 예:

```
채팅
↓
실시간 알림
↓
실시간 대시보드
↓
게임 상태 동기화
```

일반 HTTP 요청은 요청과 응답이 끝나면 연결이 종료된다.
WebSocket은 연결을 유지한다.

## 22. `com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter`

```kotlin
implementation("com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter")
```

Netflix DGS Framework를 Spring GraphQL과 함께 사용하기 위한 Starter다.

DGS는 GraphQL 서버를 만들 때 schema-first 방식과 codegen을 강하게 지원한다.

사용 흐름:

```
GraphQL schema 작성
↓
DGS Codegen으로 타입 생성
↓
DataFetcher 작성
↓
GraphQL endpoint 제공
```

주의할 점:

현재 프로젝트에는 Spring Boot 기본 GraphQL starter도 같이 있다.
둘을 같이 쓸 수는 있지만, 학습 목적이라면 하나의 방식부터 정해서 배우는 것이 좋다.

## 23. `com.vaadin:vaadin-spring-boot-starter`

```kotlin
implementation("com.vaadin:vaadin-spring-boot-starter")
```

Vaadin을 스프링부트와 함께 사용하기 위한 Starter다.

Vaadin은 자바 코드로 웹 UI를 만드는 서버 중심 UI 프레임워크다.

특징:

```
자바로 UI 컴포넌트 작성
↓
스프링 Bean과 연동
↓
프론트엔드 코드를 많이 작성하지 않아도 화면 구성 가능
```

일반 REST API 서버만 만들 것이라면 필수는 아니다.

## 24. `io.github.wimdeblauwe:htmx-spring-boot:5.1.0`

```kotlin
implementation("io.github.wimdeblauwe:htmx-spring-boot:5.1.0")
```

htmx와 스프링부트를 연동하기 위한 라이브러리다.

htmx는 HTML 속성만으로 AJAX 요청, 부분 화면 갱신 같은 기능을 만들 수 있게 해준다.

사용 예:

```
버튼 클릭
↓
서버에 HTML 조각 요청
↓
페이지 일부만 교체
```

REST API보다는 서버 렌더링 HTML과 같이 쓸 때 의미가 크다.

## 25. `org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.2`

```kotlin
implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.2")
```

Spring MVC API를 OpenAPI 문서와 Swagger UI로 보여주는 라이브러리다.

사용하면 보통 아래 주소에서 API 문서를 볼 수 있다.

```
/swagger-ui.html
/swagger-ui/index.html
/v3/api-docs
```

하는 일은 아래와 같음.

```
Controller 분석
↓
OpenAPI 스펙 생성
↓
Swagger UI 제공
↓
브라우저에서 API 테스트 가능
```

API 학습과 테스트에 매우 유용하다.

## 26. `org.springframework.data:spring-data-rest-hal-explorer`

```kotlin
implementation("org.springframework.data:spring-data-rest-hal-explorer")
```

Spring Data REST가 제공하는 HAL API를 브라우저에서 탐색할 수 있게 해주는 도구다.

HAL은 `_links`를 포함하는 JSON 표현 방식이다.

이 의존성은 보통 `spring-boot-starter-data-rest`와 함께 의미가 있다.

주의할 점:

Spring Data REST를 사용하지 않거나 Repository 자동 노출을 하지 않는다면 필요성이 낮다.

## 27. `org.springframework.modulith:spring-modulith-starter-core`

```kotlin
implementation("org.springframework.modulith:spring-modulith-starter-core")
```

Spring Modulith의 핵심 기능을 사용하기 위한 Starter다.

Modulith는 하나의 스프링부트 애플리케이션 안에서 모듈 경계를 명확하게 관리하는 데 도움을 준다.

사용 목적은 아래와 같음.

```
패키지 단위 모듈 구조 정리
↓
모듈 간 의존성 검증
↓
애플리케이션 이벤트 기반 구조 지원
```

마이크로서비스로 나누기 전, 모놀리식 애플리케이션 내부 구조를 깔끔하게 만들 때 유용하다.

## 28. `org.springframework.modulith:spring-modulith-starter-jdbc`

```kotlin
implementation("org.springframework.modulith:spring-modulith-starter-jdbc")
```

Spring Modulith의 JDBC 기반 기능을 사용하기 위한 Starter다.

주로 이벤트 발행 기록이나 Modulith 관련 저장 기능에 JDBC를 사용할 때 필요하다.

주의할 점:

JDBC를 사용하므로 DataSource 설정이 필요할 수 있다.
DB 설정이 없는 학습 프로젝트에서는 실행 시 자동 설정 문제가 생길 수 있다.

## 29. `org.springframework.security:spring-security-webauthn`

```kotlin
implementation("org.springframework.security:spring-security-webauthn")
```

WebAuthn을 지원하기 위한 Spring Security 의존성이다.

WebAuthn은 비밀번호 대신 보안 키, 생체 인증, 패스키 같은 방식으로 인증하는 표준이다.

사용 예:

```
패스키 로그인
↓
지문/얼굴 인식 기반 로그인
↓
보안 키 기반 2단계 인증
```

일반 로그인보다 고급 보안 기능에 해당한다.
처음 Spring Security를 배우는 단계에서는 기본 로그인, 세션, JWT를 먼저 이해하는 것이 좋다.
