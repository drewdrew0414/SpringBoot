### 1. 인터넷에서 웹사이트가 열리는 과정
<a id="index1"></a>

#### naver.com을 입력하면 실제로는 아래와 같은 흐름이 작동한다.
```
브라우저
↓
DNS가 도메인 이름을 서버 주소로 변환
↓
브라우저가 서버에 요청(Request)
↓
서버가 요청을 처리
↓
서버가 응답(Response)
↓
브라우저가 응답을 해석해서 화면을 그림
```

우리가 주소창에 `naver.com`이라고 입력하면 브라우저는 단순히 글자를 보여주는 것이 아니라,
서버에게 "이 주소에 해당하는 화면 또는 데이터를 주세요"라고 요청한다.

스프링부트는 이 과정에서 **서버 쪽 요청 처리**를 담당한다.
즉, 사용자가 어떤 주소로 요청했는지 확인하고, 알맞은 자바 메서드를 실행한 뒤, 결과를 다시 응답으로 돌려준다.

### 2. Request란?
<a id="index2"></a>
Request는 클라이언트가 서버에게 보내는 요청이다.

클라이언트는 보통 브라우저, 앱, 프론트엔드, Postman 같은 도구를 의미한다.
서버는 스프링부트 애플리케이션처럼 요청을 받아 처리하는 프로그램을 의미한다.

```
GET /users = "유저 목록을 주세요"
POST /users = "새 유저를 저장해주세요"
GET /users/1 = "1번 유저를 주세요"
DELETE /users/1 = "1번 유저를 삭제해주세요"
```

Request에는 보통 아래 정보가 들어간다.
```
HTTP Method : GET, POST, PUT, PATCH, DELETE
URL         : /users, /hello, /posts/1
Header      : 요청에 대한 부가 정보
Body        : 서버로 보내는 실제 데이터(JSON 등)
```

### 3. Response란?
<a id="index3"></a>
Response는 서버가 클라이언트에게 돌려주는 응답이다.

예를 들어 유저 목록을 요청하면 서버는 아래처럼 JSON 형식으로 데이터를 돌려줄 수 있다.

```json
[
  {
    "id": 1,
    "name": "kim"
  },
  {
    "id": 2,
    "name": "lee"
  }
]
```

스프링부트에서는 자바 객체를 반환하면 Jackson이라는 라이브러리가 자동으로 JSON으로 바꿔준다.
그래서 개발자는 문자열을 직접 이어 붙여 JSON을 만들 필요가 없다.

### 4. HTTP Method 기본 정리
<a id="index4"></a>
API를 만들 때는 요청의 목적에 맞게 HTTP Method를 구분해서 사용한다.

```
GET    : 데이터 조회
POST   : 데이터 생성
PUT    : 데이터 전체 수정
PATCH  : 데이터 일부 수정
DELETE : 데이터 삭제
```

예시로 보면 아래와 같다.

```
GET /users
→ 유저 전체 조회

GET /users/1
→ 1번 유저 조회

POST /users
→ 유저 생성

DELETE /users/1
→ 1번 유저 삭제
```

---

## 5. 프로젝트 구조 이해
<a id="index5"></a>
보통 스프링부트 프로젝트는 아래처럼 생김
```
src
 └ main
    ├ java
    │  └ com
    │     └ drewdrew1
    │        ├ controller
    │        ├ service
    │        ├ repository
    │        ├ entity
    │        └ dto
    └ resources
       └ application.yaml
```

`java` 안에는 실제 자바 코드가 들어간다.  
`resources` 안에는 설정 파일, 정적 파일, 템플릿 파일 등이 들어간다.

보통 스프링부트는 아래와 같은 계층 구조를 사용한다.
```
Controller : 요청 받기, 응답 돌려주기
↓
Service : 실제 기능과 규칙 처리
↓
Repository : 데이터 저장소 접근
↓
DB 또는 메모리 저장소
```

이렇게 나누는 이유는 역할을 분리하기 위해서다.
Controller에 모든 코드를 넣으면 처음에는 쉬워 보이지만, 기능이 늘어날수록 코드가 복잡해지고 수정하기 어려워진다.

---

# 목차
<a id="index"></a>
1. [인터넷에서 웹사이트가 열리는 과정](#index1)
2. [Request란?](#index2)
3. [Response란?](#index3)
4. [HTTP Method 기본 정리](#index4)
5. [프로젝트 구조 이해](#index5)
6. [스프링부트 실행 구조 이해](#index6)
7. [스프링 컨테이너 및 Bean의 이해](#index7)
8. [Controller의 이해](#index8)
9. [Service 계층 이해](#index9)
10. [Repository의 이해](#index10)
11. [Entity 이해](#index11)
12. [DTO 이해](#index12)
13. [요청 데이터 받기](#index13)
14. [응답 데이터 만들기](#index14)
15. [application.yaml 이해](#index15)
16. [예시 코드 실행 흐름](#index16)
17. [API 테스트 방법](#index17)
18. [처음 배울 때 자주 헷갈리는 부분](#index18)
19. [자주 쓰는 스프링부트 어노테이션](#index19)
20. [HTTP 요청 데이터 받는 방법](#index20)
21. [HTTP 응답 다루기](#index21)
22. [HTTP 상태 코드 정리](#index22)
23. [Header, Cookie, Content-Type 이해](#index23)
24. [예외 처리와 검증](#index24)
25. [CORS, Filter, Interceptor 이해](#index25)
26. [트랜잭션, 프로파일, 로그 기초](#index26)
27. [스프링과 스프링부트 차이](#index27)
28. [스프링부트를 쓰는 이유](#index28)
29. [Starter 의존성 이해](#index29)
30. [자동 설정과 내장 서버 이해](#index30)
31. [Gradle과 빌드 기본](#index31)
32. [스프링부트 개발 순서 기본](#index32)

---

### 6. 스프링부트 실행 구조 이해
<a id="index6"></a>
기본적으로 프로젝트를 생성하면 아래와 같은 실행 클래스가 있다. [[ 파일 찾아가기 ]](./src/main/java/com/drewdrew1/Application.java)

```java
package com.drewdrew1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### `@SpringBootApplication`이 하는 일
`@SpringBootApplication`은 스프링부트 시작 지점을 알려주는 어노테이션이다.

이 어노테이션 안에는 크게 아래 기능들이 포함되어 있다.

```
@SpringBootConfiguration
→ 이 클래스가 스프링 설정 클래스라는 뜻

@EnableAutoConfiguration
→ 필요한 설정을 스프링부트가 자동으로 잡아줌

@ComponentScan
→ 현재 패키지 아래의 컴포넌트를 자동으로 찾음
```

### `SpringApplication.run()`이 하는 일
```
스프링 애플리케이션 시작
↓
스프링 컨테이너 생성
↓
컴포넌트 스캔
↓
Bean 등록
↓
내장 톰캣 서버 실행
↓
API 요청 대기 상태 진입
```

여기서 중요한 것은 `Application.java`가 있는 패키지 위치다.

현재 프로젝트는 아래 위치에서 시작한다.

```
com.drewdrew1.Application
```

그래서 스프링은 기본적으로 `com.drewdrew1` 아래에 있는 클래스를 자동으로 검사한다.
즉, `com.drewdrew1.forExample.controller`, `com.drewdrew1.forExample.service` 같은 패키지도 자동으로 스캔된다.

---

<a id="index7"></a>
### 7-1. 스프링 컨테이너란?
스프링 컨테이너는 스프링이 객체를 생성하고 관리하는 공간이다.

일반 자바에서는 보통 아래처럼 직접 객체를 만든다.

```java
UserService userService = new UserService();
```

하지만 스프링에서는 `@Service`, `@Repository`, `@RestController` 같은 어노테이션이 붙은 클래스를 스프링이 대신 생성하고 관리한다.

```
@RestController
@Service
@Repository
@Component
@Configuration
```

이런 어노테이션이 붙은 객체들은 스프링 컨테이너에 등록될 수 있다.

### 7-2. Bean이란?
Bean은 스프링 컨테이너가 관리하는 객체를 말한다.

```java
@Service
public class UserService {
}
```

위 클래스는 `@Service`가 붙어 있으므로 스프링이 자동으로 객체를 생성한다.
그리고 그 객체를 컨테이너 안에 보관한다.
그렇게 보관된 객체를 Bean이라고 부른다.

### 7-3. 컴포넌트 스캔 흐름
프로젝트 실행 시 아래와 같은 흐름을 가진다.

```
@SpringBootApplication 실행
↓
com.drewdrew1 패키지 아래 검사 시작
↓
@RestController 발견
↓
Controller Bean 등록
↓
@Service 발견
↓
Service Bean 등록
↓
@Repository 발견
↓
Repository Bean 등록
```

컴포넌트 스캔은 쉽게 말하면 스프링이 클래스를 자동으로 찾아보는 과정이다.

```
이 클래스에 @Service 붙어있네?
→ Bean으로 등록

이 클래스에 @RestController 붙어있네?
→ Bean으로 등록
```

### 7-4. DI(의존성 주입)란?
DI는 Dependency Injection의 약자로, 의존성 주입이라는 뜻이다.

아래처럼 Controller가 Service를 필요로 한다고 해보자.

```java
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
```

여기서 `UserController`는 `UserService`가 있어야 일을 할 수 있다.
즉, `UserController`는 `UserService`에 의존한다.

스프링은 생성자의 파라미터를 보고 필요한 Bean을 자동으로 넣어준다.

```
UserController 만들려고 함
↓
생성자 확인
↓
UserService가 필요하네?
↓
스프링 컨테이너에서 UserService Bean 찾음
↓
생성자에 자동으로 넣어줌
↓
UserController 생성 완료
```

이 방식을 생성자 주입이라고 한다.

생성자 주입을 많이 쓰는 이유는 아래와 같다.

```
null 위험 감소
↓
필수 의존성을 빼먹기 어려움
↓
한 번 주입된 객체를 바꾸지 않게 만들기 쉬움
↓
테스트 코드 작성이 편함
```

---

### 8. Controller의 이해
<a id="index8"></a>

Controller는 클라이언트의 요청을 받는 역할을 한다.  
보통 `controller` 패키지 안에 만든다. [[ 예시 파일 찾아가기 ]](./src/main/java/com/drewdrew1/forExample/controller/HelloController.java)

```java
package com.drewdrew1.forExample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello spring";
    }
}
```

### 코드 분석하기
`@RestController`는 이 클래스가 API 요청을 처리하는 클래스라는 뜻이다.

`@RequestMapping("/example")`은 이 Controller 안의 API 주소 앞에 공통으로 `/example`을 붙인다는 뜻이다.

`@GetMapping("/hello")`는 GET 방식으로 `/example/hello` 주소가 들어왔을 때 이 메서드를 실행하라는 뜻이다.

`return "hello spring";`은 클라이언트에게 문자열을 그대로 응답한다는 뜻이다.

요청 처리 흐름은 아래와 같다.

```
브라우저에서 http://localhost:8080/example/hello 접속
↓
GET /example/hello 요청 발생
↓
스프링이 요청 URL 검색
↓
@RequestMapping("/example") + @GetMapping("/hello") 발견
↓
hello() 메서드 실행
↓
"hello spring" 반환
↓
브라우저에 출력
```

### Controller가 하면 좋은 일
Controller는 HTTP와 가장 가까운 계층이다.
그래서 아래 일을 담당하는 것이 좋다.

```
요청 URL 연결
↓
요청 데이터 받기
↓
Service 호출
↓
응답 데이터 반환
```

반대로 Controller에 복잡한 계산, 중복 검사, 저장 규칙 같은 기능을 많이 넣으면 코드가 금방 지저분해진다.
그런 코드는 Service로 보내는 것이 좋다.

---

### 9. Service 계층 이해
<a id="index9"></a>
Service는 실제 기능과 규칙을 처리하는 계층이다.  
보통 `service` 패키지 안에 만든다. [[ 예시 파일 찾아가기 ]](./src/main/java/com/drewdrew1/forExample/service/UserService.java)

처음 배우면 아래처럼 Controller에 모든 코드를 넣고 싶어질 수 있다.

```java
@RestController
public class UserController {

    @PostMapping("/signup")
    public String signup() {

        // 회원가입 데이터 확인
        // 이름 중복 검사
        // DB 저장
        // 가입 완료 메시지 생성

        return "ok";
    }
}
```

위 방식은 작은 예제에서는 동작하지만, 실제 프로젝트에서는 Controller가 너무 많은 일을 하게 된다.

그래서 아래처럼 역할을 나눈다.

```
Controller
→ 요청/응답 담당

Service
→ 실제 기능, 정책, 조건 검사 담당

Repository
→ 데이터 저장 및 조회 담당
```

### `@Service`란?
`@Service`는 이 클래스가 비즈니스 로직을 담당하는 객체라는 뜻이다.

```java
@Service
public class UserService {
}
```

`@Service`가 붙으면 스프링이 이 클래스를 Bean으로 등록한다.
그리고 Controller에서 생성자 주입으로 가져다 쓸 수 있다.

### Service에 들어가기 좋은 코드
```
회원가입 가능 여부 검사
↓
이름이 비어있는지 확인
↓
나이가 올바른 값인지 확인
↓
Repository에 저장 요청
↓
Entity를 Response DTO로 변환
```

즉, Service는 "이 기능이 어떤 순서와 규칙으로 동작해야 하는가"를 담는 계층이다.

---

### 10. Repository의 이해
<a id="index10"></a>
Repository는 데이터 저장소에 접근하는 계층이다.  
보통 `repository` 패키지 안에 만든다. [[ 예시 파일 찾아가기 ]](./src/main/java/com/drewdrew1/forExample/repository/UserRepository.java)

데이터 저장소는 실제 DB일 수도 있고, 예제처럼 메모리 안의 `Map`일 수도 있다.

Repository를 분리하는 이유는 Controller나 Service가 데이터 저장 방식에 너무 직접적으로 묶이지 않게 하기 위해서다.

아래는 좋지 않은 구조 예시다.

```java
@RestController
public class UserController {

    @GetMapping("/users")
    public String users() {

        // SQL 작성
        // DB 연결
        // 결과 변환
        // 응답 반환

        return "ok";
    }
}
```

문제점은 요청 처리, 비즈니스 로직, DB 처리가 한 곳에 섞여 있다는 것이다.

그래서 아래처럼 분리한다.

```
Controller
→ HTTP 요청 처리

Service
→ 기능 흐름 처리

Repository
→ 데이터 저장소 처리
```

### 10-1. JPA란?
원래 DB를 사용하려면 SQL을 직접 작성해야 한다.

```sql
SELECT * FROM users;
```

JPA를 사용하면 DB 테이블을 자바 객체처럼 다루는 방식으로 코드를 작성할 수 있다.

```java
userRepository.findAll();
```

JPA는 아래 같은 일을 도와준다.

```
자바 객체를 DB 테이블과 연결
↓
저장, 조회, 삭제 같은 기본 기능 제공
↓
SQL 작성량 감소
↓
객체 중심 코드 작성 가능
```

단, JPA는 DB 연결과 관련 의존성이 필요하다.
이번 예시 코드는 처음 구조를 이해하기 쉽도록 실제 DB 대신 메모리 저장소를 사용한다.

---

### 11. Entity 이해
<a id="index11"></a>
Entity는 핵심 데이터를 표현하는 객체다.  
보통 `entity` 패키지 안에 만든다. [[ 예시 파일 찾아가기 ]](./src/main/java/com/drewdrew1/forExample/entity/User.java)

DB를 사용하는 프로젝트에서는 Entity가 DB 테이블과 연결되는 경우가 많다.

```java
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
```

위 코드는 대략 아래 테이블과 연결된다고 볼 수 있다.

```sql
create table user (
    id bigint,
    name varchar(255)
);
```

### Entity를 바로 응답으로 쓰지 않는 이유
처음에는 Entity를 바로 Controller에서 반환해도 동작한다.
하지만 실제 프로젝트에서는 보통 Entity를 바로 응답으로 내보내지 않는다.

이유는 아래와 같다.

```
DB 구조가 외부 API에 그대로 노출됨
↓
비밀번호 같은 민감 정보가 같이 나갈 위험 있음
↓
DB 컬럼을 바꾸면 API 응답도 같이 흔들림
↓
요청용 데이터와 응답용 데이터를 분리하기 어려움
```

그래서 Entity는 내부 데이터 모델로 두고, 외부 요청/응답에는 DTO를 사용한다.

---

### 12. DTO 이해
<a id="index12"></a>
DTO는 Data Transfer Object의 약자로, 데이터 전달용 객체라는 뜻이다.  
보통 `dto` 패키지 안에 만든다.

이번 예시에는 요청 DTO와 응답 DTO가 따로 있다.

```
UserCreateRequest
→ 클라이언트가 서버로 보내는 회원 생성 요청 데이터

UserResponse
→ 서버가 클라이언트에게 돌려주는 회원 응답 데이터
```

요청 DTO 예시 [[ 파일 찾아가기 ]](./src/main/java/com/drewdrew1/forExample/dto/UserCreateRequest.java)

```java
public class UserCreateRequest {

    private String name;
    private int age;

    public UserCreateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

응답 DTO 예시 [[ 파일 찾아가기 ]](./src/main/java/com/drewdrew1/forExample/dto/UserResponse.java)

```java
public class UserResponse {

    private Long id;
    private String name;
    private int age;
}
```

### 왜 기본 생성자가 필요할까?
스프링은 JSON 요청을 자바 객체로 바꿀 때 Jackson을 사용한다.

Jackson은 보통 아래 순서로 동작한다.

```
JSON 요청 도착
↓
기본 생성자로 DTO 객체 생성
↓
setName(), setAge() 같은 setter 호출
↓
DTO에 값 채움
↓
Controller 메서드에 전달
```

그래서 요청 DTO에는 기본 생성자와 setter를 만들어두는 방식이 자주 사용된다.

---

### 13. 요청 데이터 받기
<a id="index13"></a>
클라이언트가 서버로 JSON 데이터를 보낼 때는 `@RequestBody`를 사용한다.

```java
@PostMapping("/users")
public UserResponse createUser(@RequestBody UserCreateRequest request) {
    return userService.createUser(request);
}
```

위 코드의 의미는 아래와 같다.

```
POST /users 요청 도착
↓
요청 Body에 JSON이 있음
↓
@RequestBody가 JSON을 UserCreateRequest로 변환
↓
Controller가 Service에 전달
```

예시 요청 JSON은 아래와 같다.

```json
{
  "name": "park",
  "age": 30
}
```

이 JSON은 자바에서 아래 객체처럼 변환된다.

```java
UserCreateRequest request = new UserCreateRequest();
request.setName("park");
request.setAge(30);
```

---

### 14. 응답 데이터 만들기
<a id="index14"></a>
스프링부트에서는 Controller가 객체를 반환하면 자동으로 JSON 응답을 만들어준다.

```java
@GetMapping("/users")
public List<UserResponse> findUsers() {
    return userService.findUsers();
}
```

위 메서드가 `List<UserResponse>`를 반환하면 클라이언트는 아래처럼 JSON 배열을 받는다.

```json
[
  {
    "id": 1,
    "name": "kim",
    "age": 20
  },
  {
    "id": 2,
    "name": "lee",
    "age": 25
  }
]
```

스프링 내부 흐름은 아래와 같다.

```
Controller가 자바 객체 반환
↓
Jackson이 객체를 JSON으로 변환
↓
HTTP Response Body에 JSON 담김
↓
클라이언트가 JSON 응답 받음
```

---

### 15. application.yaml 이해
<a id="index15"></a>
`application.yaml`은 스프링부트 설정 파일이다. [[ 파일 찾아가기 ]](./src/main/resources/application.yaml)

현재 프로젝트에는 아래처럼 애플리케이션 이름 설정이 들어 있다.

```yaml
spring:
  application:
    name: SpringBoot
```

설정 파일에는 보통 아래 같은 내용이 들어간다.

```
서버 포트
DB 연결 정보
로그 레벨
프로파일 설정
외부 API 키
파일 업로드 제한
```

예를 들어 서버 포트를 바꾸고 싶으면 아래처럼 작성할 수 있다.

```yaml
server:
  port: 8081
```

그러면 기본 포트인 `8080`이 아니라 `8081`로 실행된다.

---

### 16. 예시 코드 실행 흐름
<a id="index16"></a>
이번 프로젝트의 예시 코드는 아래 패키지에 들어 있다.

```
src/main/java/com/drewdrew1/forExample
```

예시 코드 구조는 아래와 같다.

```
forExample
 ├ config
 │  └ ExampleSecurityConfig.java
 ├ controller
 │  └ HelloController.java
 │  └ HttpExampleController.java
 ├ dto
 │  ├ UserCreateRequest.java
 │  └ UserResponse.java
 ├ entity
 │  └ User.java
 ├ repository
 │  └ UserRepository.java
 └ service
    └ UserService.java
```

유저 목록 조회 흐름은 아래와 같다.

```
GET /example/users 요청
↓
HelloController.findUsers()
↓
UserService.findUsers()
↓
UserRepository.findAll()
↓
User Entity 목록 조회
↓
UserResponse DTO 목록으로 변환
↓
JSON 응답 반환
```

유저 생성 흐름은 아래와 같다.

```
POST /example/users 요청
↓
JSON Body 전송
↓
@RequestBody가 UserCreateRequest로 변환
↓
HelloController.createUser()
↓
UserService.createUser()
↓
이름, 나이 검증
↓
UserRepository.save()
↓
User Entity 생성 및 저장
↓
UserResponse로 변환
↓
JSON 응답 반환
```

이번 예시에서 Repository는 실제 DB가 아니라 메모리 `Map`에 데이터를 저장한다.
그래서 서버를 재시작하면 저장된 데이터는 초기화된다.
이 방식은 실무 저장 방식은 아니지만, Controller-Service-Repository 흐름을 배우기에는 좋다.

HTTP 기능 예시는 아래 파일에 들어 있다. [[ 파일 찾아가기 ]](./src/main/java/com/drewdrew1/forExample/controller/HttpExampleController.java)

```
GET /example/http/query?keyword=spring&page=2
→ Query Parameter 예시

GET /example/http/path/books/10
→ Path Variable 예시

GET /example/http/headers
→ Request Header 예시

GET /example/http/cookies
→ Cookie 예시

GET /example/http/response-entity
→ ResponseEntity 예시
```

---

### 17. API 테스트 방법
<a id="index17"></a>
서버를 실행한 뒤 브라우저 또는 Postman으로 테스트할 수 있다.

Gradle로 실행하려면 아래 명령어를 사용한다.

```bash
./gradlew bootRun
```

윈도우 PowerShell에서는 아래처럼 실행할 수 있다.

```powershell
.\gradlew.bat bootRun
```

현재 `build.gradle.kts`에는 JDBC, Session JDBC, MySQL 같은 DB 관련 의존성이 들어 있다.
예시 코드는 DB 없이 메모리 저장소로 동작하지만, 프로젝트 전체 실행이나 테스트 과정에서는 환경에 따라 DataSource 설정을 요구할 수 있다.
그럴 때는 MySQL 연결 정보를 `application.yaml`에 넣거나, `compose.yaml`의 Docker Compose 환경을 사용해야 한다.

### 브라우저에서 확인 가능한 요청
```
http://localhost:8080/example/hello
http://localhost:8080/example/users
http://localhost:8080/example/users/1
http://localhost:8080/example/http/query?keyword=spring&page=2
http://localhost:8080/example/http/path/books/10
http://localhost:8080/example/http/headers
http://localhost:8080/example/http/response-entity
```

### PowerShell로 POST 요청 보내기
```powershell
Invoke-RestMethod `
  -Method Post `
  -Uri "http://localhost:8080/example/users" `
  -ContentType "application/json" `
  -Body '{"name":"park","age":30}'
```

정상 응답 예시는 아래와 같다.

```json
{
  "id": 3,
  "name": "park",
  "age": 30
}
```

---

### 18. 처음 배울 때 자주 헷갈리는 부분
<a id="index18"></a>

### `@Controller`와 `@RestController` 차이
`@Controller`는 보통 HTML 화면을 반환할 때 사용한다.  
`@RestController`는 보통 JSON 데이터를 반환하는 API를 만들 때 사용한다.

```
@Controller
→ HTML 화면 반환에 자주 사용

@RestController
→ JSON API 반환에 자주 사용
```

### `@GetMapping`과 `@PostMapping` 차이
`@GetMapping`은 조회할 때 사용한다.  
`@PostMapping`은 새 데이터를 만들 때 사용한다.

```
@GetMapping("/users")
→ 유저 조회

@PostMapping("/users")
→ 유저 생성
```

### `@PathVariable`과 `@RequestBody` 차이
`@PathVariable`은 URL 경로에 들어간 값을 꺼낼 때 사용한다.

```java
@GetMapping("/users/{id}")
public UserResponse findUser(@PathVariable Long id) {
    return userService.findUser(id);
}
```

`@RequestBody`는 요청 Body에 들어온 JSON을 자바 객체로 바꿀 때 사용한다.

```java
@PostMapping("/users")
public UserResponse createUser(@RequestBody UserCreateRequest request) {
    return userService.createUser(request);
}
```

### 전체 흐름 최종 정리
```
클라이언트
↓
HTTP Request
↓
Controller
↓
Service
↓
Repository
↓
Entity 또는 DB
↓
Repository
↓
Service
↓
DTO
↓
Controller
↓
HTTP Response(JSON)
↓
클라이언트
```

스프링부트의 핵심은 "요청이 들어오면 어떤 객체가 어떤 순서로 일하는가"를 이해하는 것이다.
처음에는 어노테이션이 많아 보여도, 결국 큰 흐름은 Controller가 받고, Service가 처리하고, Repository가 데이터를 다루는 구조다.

---

### 19. 자주 쓰는 스프링부트 어노테이션
<a id="index19"></a>

스프링부트에서는 어노테이션을 많이 사용한다.
어노테이션은 쉽게 말하면 "이 클래스나 메서드를 스프링이 이런 의미로 봐줘"라고 표시하는 문법이다.

### 실행 및 설정 관련 어노테이션
```
@SpringBootApplication
→ 스프링부트 애플리케이션 시작 지점

@Configuration
→ 설정 클래스를 의미

@Bean
→ 개발자가 직접 만든 객체를 스프링 Bean으로 등록

@Value
→ application.yaml 값 하나를 주입받을 때 사용

@ConfigurationProperties
→ application.yaml의 묶음 설정을 객체로 받을 때 사용
```

예시로 보면 아래와 같다.

```java
@Configuration
public class AppConfig {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
```

위 코드는 `Clock` 객체를 스프링 Bean으로 등록한다.
이후 다른 클래스에서 생성자 주입으로 `Clock`을 받을 수 있다.

### 계층 관련 어노테이션
```
@RestController
→ JSON API Controller

@Controller
→ HTML 화면을 반환하는 Controller

@Service
→ 비즈니스 로직 담당

@Repository
→ DB 또는 저장소 접근 담당

@Component
→ 일반적인 스프링 Bean
```

사실 `@RestController`, `@Service`, `@Repository`는 내부적으로 Component 계열이다.
즉, 스프링이 자동으로 찾아서 Bean으로 등록할 수 있다.

### HTTP 요청 매핑 어노테이션
```
@RequestMapping
→ 공통 URL 또는 여러 HTTP Method 연결

@GetMapping
→ GET 요청 연결

@PostMapping
→ POST 요청 연결

@PutMapping
→ PUT 요청 연결

@PatchMapping
→ PATCH 요청 연결

@DeleteMapping
→ DELETE 요청 연결
```

예시:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public List<UserResponse> findUsers() {
        return userService.findUsers();
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }
}
```

위 코드는 아래처럼 해석된다.

```
GET /users
→ 전체 유저 조회

POST /users
→ 새 유저 생성
```

### 요청 데이터 관련 어노테이션
```
@PathVariable
→ URL 경로 값을 꺼냄

@RequestParam
→ Query Parameter 값을 꺼냄

@RequestBody
→ JSON Body를 자바 객체로 변환

@RequestHeader
→ HTTP Header 값을 꺼냄

@CookieValue
→ Cookie 값을 꺼냄

@ModelAttribute
→ 폼 데이터나 Query Parameter를 객체로 묶음
```

### 응답 관련 어노테이션
```
@ResponseStatus
→ 정상 응답 상태 코드를 직접 지정

@ResponseBody
→ 반환값을 HTTP Body에 직접 담음

@RestController
→ @Controller + @ResponseBody
```

`@RestController`를 쓰면 메서드 반환값이 HTML 이름으로 해석되지 않고 JSON 또는 문자열 응답으로 나간다.

### 예외 처리 관련 어노테이션
```
@ExceptionHandler
→ 특정 예외가 발생했을 때 실행할 메서드 지정

@ControllerAdvice
→ 여러 Controller에 공통 예외 처리 적용

@RestControllerAdvice
→ JSON API용 공통 예외 처리 적용
```

### DB 관련 어노테이션
```
@Transactional
→ 메서드를 하나의 트랜잭션으로 묶음

@Entity
→ JPA에서 DB 테이블과 연결되는 객체

@Id
→ 기본키(PK)

@GeneratedValue
→ 기본키 자동 생성

@Column
→ DB 컬럼 설정

@ManyToOne
→ 다대일 관계

@OneToMany
→ 일대다 관계
```

### 테스트 관련 어노테이션
```
@SpringBootTest
→ 스프링 컨테이너를 실제로 띄워서 테스트

@WebMvcTest
→ Controller 계층 중심 테스트

@Test
→ 테스트 메서드 표시
```

---

### 20. HTTP 요청 데이터 받는 방법
<a id="index20"></a>

HTTP 요청에서 서버가 받을 수 있는 데이터는 여러 위치에 있다.
위치를 구분해서 이해하면 Controller 코드를 훨씬 쉽게 읽을 수 있다.

```
URL 경로
→ /users/1

Query Parameter
→ /users?name=kim&page=1

Header
→ Authorization, Content-Type, User-Agent

Cookie
→ JSESSIONID 같은 브라우저 저장 값

Body
→ JSON, form-data 같은 실제 전송 데이터
```

### `@PathVariable`
URL 경로에 들어간 값을 꺼낼 때 사용한다.

```java
@GetMapping("/users/{id}")
public UserResponse findUser(@PathVariable Long id) {
    return userService.findUser(id);
}
```

요청:
```
GET /users/10
```

결과:
```
id = 10
```

주로 특정 자원 하나를 조회할 때 사용한다.

```
GET /users/1
GET /posts/3
GET /orders/100
```

### `@RequestParam`
Query Parameter를 꺼낼 때 사용한다.

```java
@GetMapping("/search")
public String search(@RequestParam String keyword,
                     @RequestParam(defaultValue = "1") int page) {
    return keyword + " / " + page;
}
```

요청:
```
GET /search?keyword=spring&page=2
```

결과:
```
keyword = spring
page = 2
```

검색, 필터, 정렬, 페이지 번호처럼 조회 조건을 보낼 때 자주 사용한다.

### `@RequestBody`
HTTP Body에 들어온 JSON을 자바 객체로 바꿀 때 사용한다.

```java
@PostMapping("/users")
public UserResponse createUser(@RequestBody UserCreateRequest request) {
    return userService.createUser(request);
}
```

요청:

```json
{
  "name": "kim",
  "age": 20
}
```

흐름:
```
JSON Body
↓
Jackson
↓
UserCreateRequest 객체
↓
Controller 메서드 파라미터
```

### `@RequestHeader`
HTTP Header 값을 꺼낼 때 사용한다.

```java
@GetMapping("/headers")
public String header(@RequestHeader("User-Agent") String userAgent) {
    return userAgent;
}
```

Header는 요청 본문 데이터가 아니라 요청에 대한 부가 정보다.

```
Content-Type
→ 내가 보내는 데이터 형식

Accept
→ 내가 받고 싶은 응답 형식

Authorization
→ 인증 정보

User-Agent
→ 요청을 보낸 브라우저 또는 클라이언트 정보
```

### `@CookieValue`
Cookie 값을 꺼낼 때 사용한다.

```java
@GetMapping("/cookies")
public String cookie(@CookieValue(value = "JSESSIONID", required = false) String sessionId) {
    return sessionId;
}
```

Cookie는 브라우저가 같은 서버에 요청할 때 자동으로 같이 보낼 수 있는 값이다.
로그인 세션, 사용자 설정값 같은 기능에서 사용된다.

### `HttpServletRequest`
요청 원본 정보가 필요할 때 사용할 수 있다.

```java
@GetMapping("/request-info")
public String requestInfo(HttpServletRequest request) {
    return request.getMethod() + " " + request.getRequestURI();
}
```

하지만 스프링에서는 가능하면 `@PathVariable`, `@RequestParam`, `@RequestBody` 같은 어노테이션으로 필요한 값만 받는 편이 코드가 더 깔끔하다.

---

### 21. HTTP 응답 다루기
<a id="index21"></a>

스프링부트에서 Controller가 값을 반환하면 그 값이 HTTP 응답으로 변환된다.

```java
@GetMapping("/hello")
public String hello() {
    return "hello spring";
}
```

위 코드는 문자열을 응답 Body에 담아 보낸다.

객체를 반환하면 JSON으로 변환된다.

```java
@GetMapping("/user")
public UserResponse user() {
    return new UserResponse(1L, "kim", 20);
}
```

응답:

```json
{
  "id": 1,
  "name": "kim",
  "age": 20
}
```

### `ResponseEntity`
응답 상태 코드, Header, Body를 직접 지정하고 싶을 때 사용한다.

```java
@GetMapping("/response")
public ResponseEntity<String> response() {
    return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .header("X-Example", "spring")
            .body("ok");
}
```

위 코드는 아래처럼 응답한다.

```
Status : 202 Accepted
Header : X-Example: spring
Body   : ok
```

### `@ResponseStatus`
정상 처리 후 특정 상태 코드를 반환하고 싶을 때 사용한다.

```java
@PostMapping("/users")
@ResponseStatus(HttpStatus.CREATED)
public UserResponse createUser(@RequestBody UserCreateRequest request) {
    return userService.createUser(request);
}
```

위 코드는 성공 시 `201 Created`로 응답한다.

### 언제 `ResponseEntity`를 쓰면 좋을까?
```
상태 코드를 직접 바꾸고 싶을 때
↓
응답 Header를 추가하고 싶을 때
↓
Body가 없는 응답을 만들고 싶을 때
↓
파일 다운로드 같은 응답을 만들 때
```

Body가 없는 응답 예시:

```java
@DeleteMapping("/users/{id}")
public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
}
```

`204 No Content`는 요청이 성공했지만 응답 Body가 없다는 뜻이다.

---

### 22. HTTP 상태 코드 정리
<a id="index22"></a>

HTTP 상태 코드는 서버가 요청을 어떻게 처리했는지 알려주는 숫자다.

```
2xx
→ 성공

3xx
→ 리다이렉트

4xx
→ 클라이언트 요청 문제

5xx
→ 서버 내부 문제
```

### 자주 쓰는 성공 상태 코드
```
200 OK
→ 요청 성공, 응답 Body 있음

201 Created
→ 생성 성공

204 No Content
→ 성공했지만 응답 Body 없음
```

예시:
```
GET /users
→ 200 OK

POST /users
→ 201 Created

DELETE /users/1
→ 204 No Content
```

### 자주 쓰는 클라이언트 오류 상태 코드
```
400 Bad Request
→ 요청 값이 잘못됨

401 Unauthorized
→ 인증이 필요함

403 Forbidden
→ 인증은 됐지만 권한이 없음

404 Not Found
→ 요청한 자원이 없음

409 Conflict
→ 이미 존재하는 데이터 등 충돌 발생
```

### 자주 쓰는 서버 오류 상태 코드
```
500 Internal Server Error
→ 서버 내부 오류

503 Service Unavailable
→ 서버가 일시적으로 요청 처리 불가
```

API를 만들 때 중요한 것은 아무 상태 코드나 쓰는 것이 아니라,
클라이언트가 결과를 이해할 수 있게 적절한 상태 코드를 주는 것이다.

---

### 23. Header, Cookie, Content-Type 이해
<a id="index23"></a>

HTTP 요청과 응답은 크게 Start Line, Header, Body로 나눌 수 있다.

```
Start Line
→ GET /users HTTP/1.1

Header
→ Content-Type, Accept, Authorization 등

Body
→ JSON 데이터 등
```

### Header란?
Header는 요청이나 응답에 대한 부가 정보다.

예시:

```
Content-Type: application/json
Accept: application/json
Authorization: Bearer token-value
User-Agent: Mozilla/5.0
```

### `Content-Type`
`Content-Type`은 내가 보내는 Body 데이터의 형식을 의미한다.

```
Content-Type: application/json
→ Body가 JSON 형식

Content-Type: application/x-www-form-urlencoded
→ HTML form 방식

Content-Type: multipart/form-data
→ 파일 업로드 방식
```

JSON 요청을 보낼 때 `Content-Type: application/json`을 빼먹으면 서버가 Body를 제대로 해석하지 못할 수 있다.

### `Accept`
`Accept`는 클라이언트가 받고 싶은 응답 형식을 의미한다.

```
Accept: application/json
→ JSON으로 응답 받고 싶음

Accept: text/html
→ HTML로 응답 받고 싶음
```

### Cookie란?
Cookie는 브라우저에 저장되는 작은 데이터다.
브라우저는 같은 서버에 요청할 때 Cookie를 자동으로 같이 보낼 수 있다.

```
서버가 Set-Cookie 응답
↓
브라우저가 Cookie 저장
↓
다음 요청부터 Cookie 자동 전송
↓
서버가 Cookie로 사용자 구분 가능
```

세션 로그인에서는 보통 `JSESSIONID` 같은 Cookie가 사용된다.

### Header와 Body 차이
```
Header
→ 요청/응답에 대한 설명

Body
→ 실제 전송하려는 데이터
```

예를 들어 회원가입 요청이라면 아래처럼 볼 수 있다.

```
Header
→ JSON을 보냅니다

Body
→ name, age 값을 보냅니다
```

---

### 24. 예외 처리와 검증
<a id="index24"></a>

API에서는 잘못된 요청이 들어올 수 있다.

예를 들어 아래 요청은 문제가 있다.

```json
{
  "name": "",
  "age": -10
}
```

이름이 비어 있고 나이가 음수이기 때문이다.
이런 경우 서버는 그냥 500 에러를 내는 것이 아니라, 클라이언트에게 요청이 잘못됐다는 의미의 `400 Bad Request`를 돌려주는 것이 좋다.

### 간단한 예외 처리
현재 예시 코드에서는 `ResponseStatusException`을 사용한다.

```java
if (name.isBlank()) {
    throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "name은 비어 있을 수 없습니다."
    );
}
```

이렇게 하면 스프링이 `400 Bad Request` 응답을 만들어준다.

### `@ExceptionHandler`
특정 Controller 안에서 예외를 처리하고 싶을 때 사용한다.

```java
@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
}
```

### `@RestControllerAdvice`
여러 Controller에서 공통으로 예외를 처리하고 싶을 때 사용한다.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
```

흐름은 아래와 같다.

```
Controller 또는 Service에서 예외 발생
↓
스프링이 예외 처리 메서드 검색
↓
@ExceptionHandler 발견
↓
에러 응답 생성
↓
클라이언트에게 JSON 또는 문자열 응답
```

### `@Valid`
요청 DTO 검증에는 `@Valid`를 자주 사용한다.

```java
public class UserCreateRequest {

    @NotBlank
    private String name;

    @Min(0)
    private int age;
}
```

Controller에서는 아래처럼 쓴다.

```java
@PostMapping("/users")
public UserResponse createUser(@Valid @RequestBody UserCreateRequest request) {
    return userService.createUser(request);
}
```

`@Valid`를 제대로 사용하려면 Bean Validation 관련 의존성이 필요하다.
보통은 `spring-boot-starter-validation`을 추가해서 사용한다.

---

### 25. CORS, Filter, Interceptor 이해
<a id="index25"></a>

### CORS란?
CORS는 Cross-Origin Resource Sharing의 약자다.
브라우저가 다른 출처의 서버로 요청을 보낼 때 적용되는 보안 정책이다.

출처는 보통 아래 3개로 구분된다.

```
프로토콜
→ http, https

도메인
→ localhost, example.com

포트
→ 3000, 8080
```

예를 들어 프론트엔드가 `http://localhost:3000`에서 실행되고,
스프링부트 서버가 `http://localhost:8080`에서 실행되면 포트가 다르므로 다른 출처다.

이때 브라우저가 CORS 정책 때문에 요청을 막을 수 있다.

간단한 Controller 단위 설정 예시는 아래와 같다.

```java
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
}
```

실무에서는 보통 전체 CORS 설정을 Security 설정 또는 WebMvcConfigurer에서 관리한다.

### Filter란?
Filter는 스프링 MVC에 요청이 들어가기 전 단계에서 동작한다.

```
HTTP 요청
↓
Filter
↓
DispatcherServlet
↓
Interceptor
↓
Controller
```

Filter는 보통 아래 기능에 사용된다.

```
인증 토큰 검사
요청/응답 로그
인코딩 처리
보안 처리
```

### Interceptor란?
Interceptor는 Controller 실행 전후에 동작한다.

```
preHandle
→ Controller 실행 전

postHandle
→ Controller 실행 후, View 렌더링 전

afterCompletion
→ 요청 처리 완료 후
```

Interceptor는 보통 아래 기능에 사용된다.

```
로그인 여부 확인
권한 체크
요청 로그
공통 데이터 세팅
```

### Filter와 Interceptor 차이
```
Filter
→ 스프링 MVC 바깥쪽에서 먼저 동작

Interceptor
→ 스프링 MVC 안쪽에서 Controller 전후로 동작
```

Spring Security는 내부적으로 Filter 기반으로 동작한다.
그래서 인증/인가 처리는 보통 Security Filter Chain에서 처리한다.

---

### 26. 트랜잭션, 프로파일, 로그 기초
<a id="index26"></a>

### 트랜잭션이란?
트랜잭션은 여러 DB 작업을 하나의 작업 단위로 묶는 것이다.

예를 들어 회원가입을 할 때 아래 작업들이 있다고 해보자.

```
회원 저장
↓
가입 포인트 지급
↓
가입 로그 저장
```

중간에 하나라도 실패하면 앞의 작업도 취소되어야 데이터가 이상해지지 않는다.
이럴 때 트랜잭션을 사용한다.

```java
@Transactional
public void signup(SignupRequest request) {
    userRepository.save(user);
    pointRepository.save(point);
    logRepository.save(log);
}
```

흐름:

```
메서드 시작
↓
트랜잭션 시작
↓
DB 작업 실행
↓
전부 성공하면 commit
↓
중간에 실패하면 rollback
```

### 프로파일이란?
프로파일은 실행 환경별 설정을 나누는 기능이다.

보통 아래처럼 나눈다.

```
local
→ 내 컴퓨터 개발 환경

dev
→ 개발 서버

prod
→ 실제 운영 서버
```

파일 예시:

```
application-local.yaml
application-prod.yaml
```

실행할 때 프로파일을 지정할 수 있다.

```powershell
.\gradlew.bat bootRun --args='--spring.profiles.active=local'
```

### 로그란?
로그는 프로그램이 실행되는 동안 어떤 일이 일어났는지 기록하는 것이다.

처음에는 `System.out.println()`을 많이 쓰지만,
실제 프로젝트에서는 Logger를 사용하는 것이 좋다.

```java
private static final Logger log = LoggerFactory.getLogger(UserService.class);

log.info("회원 생성 요청 name={}", name);
```

로그 레벨은 보통 아래처럼 나뉜다.

```
trace
→ 매우 자세한 개발용 로그

debug
→ 디버깅용 로그

info
→ 일반적인 실행 정보

warn
→ 문제가 될 수 있는 상황

error
→ 실제 오류
```

`application.yaml`에서 로그 레벨을 설정할 수 있다.

```yaml
logging:
  level:
    com.drewdrew1: debug
```

처음에는 Controller, Service, Repository 흐름을 이해하고,
그 다음 HTTP 요청/응답, 예외 처리, 검증, 트랜잭션 순서로 배우면 구조가 훨씬 잘 잡힌다.

---

### 27. 스프링과 스프링부트 차이
<a id="index27"></a>

스프링부트를 이해하려면 먼저 스프링이 무엇인지 알아야 한다.

### 스프링이란?
스프링은 자바로 서버 프로그램을 만들 때 많이 사용하는 프레임워크다.

프레임워크는 개발자가 매번 직접 만들기 귀찮고 어려운 공통 기능을 미리 제공하는 도구라고 보면 된다.

스프링이 도와주는 대표적인 기능은 아래와 같다.

```
객체 생성과 관리
↓
의존성 주입
↓
웹 요청 처리
↓
DB 접근
↓
트랜잭션 처리
↓
보안 처리
↓
테스트 지원
```

즉, 스프링은 서버 개발에 필요한 뼈대를 제공한다.

### 스프링부트란?
스프링부트는 스프링을 더 쉽게 사용할 수 있게 만든 도구다.

스프링만 사용할 때는 설정해야 할 것이 많다.
예전에는 서버를 띄우기 위해 XML 설정, 톰캣 설정, 라이브러리 버전 관리 등을 직접 많이 해야 했다.

스프링부트는 이런 번거로운 설정을 줄여준다.

```
스프링
→ 기능은 강력하지만 설정이 많음

스프링부트
→ 스프링을 빠르게 시작할 수 있게 기본 설정을 자동으로 제공
```

### 쉽게 비교하면
```
스프링
→ 재료와 도구를 많이 주는 방식

스프링부트
→ 자주 쓰는 조합을 미리 준비해주는 방식
```

스프링부트는 스프링을 대체하는 것이 아니다.
스프링부트는 스프링 위에서 동작하면서, 스프링을 편하게 쓰게 해주는 것이다.

### 스프링부트가 해주는 것
```
의존성 버전 관리
↓
자동 설정
↓
내장 톰캣 제공
↓
실행 가능한 jar 생성
↓
운영에 필요한 기본 기능 제공
```

예를 들어 웹 서버를 만들고 싶으면 `spring-boot-starter-webmvc` 같은 의존성을 추가한다.
그러면 스프링부트가 웹 서버에 필요한 설정을 대부분 자동으로 잡아준다.

---

### 28. 스프링부트를 쓰는 이유
<a id="index28"></a>

스프링부트를 쓰는 가장 큰 이유는 빠르게 서버를 만들 수 있기 때문이다.

### 1. 설정이 줄어든다
스프링부트는 프로젝트에 어떤 라이브러리가 있는지 보고 필요한 설정을 자동으로 적용한다.

예를 들어 웹 관련 의존성이 있으면 아래 기능들이 자동으로 준비된다.

```
내장 톰캣
↓
DispatcherServlet
↓
JSON 변환기
↓
Controller 매핑
↓
기본 에러 처리
```

그래서 개발자는 설정보다 기능 구현에 더 집중할 수 있다.

### 2. 서버 실행이 쉽다
스프링부트는 내장 서버를 포함한다.

그래서 따로 톰캣을 설치하고 배포하지 않아도 아래처럼 실행할 수 있다.

```powershell
.\gradlew.bat bootRun
```

또는 빌드 후 jar 파일로 실행할 수도 있다.

```powershell
java -jar build/libs/SpringBoot-0.0.1-SNAPSHOT.jar
```

### 3. 의존성 관리가 편하다
스프링부트는 자주 쓰는 라이브러리 조합을 Starter로 제공한다.

예를 들어 웹 API를 만들려면 필요한 라이브러리가 많다.

```
Spring MVC
Jackson
Tomcat
Validation
Logging
```

이것을 하나씩 직접 버전 맞춰 넣으면 귀찮고 실수하기 쉽다.
스프링부트 Starter를 사용하면 관련 라이브러리가 묶음으로 들어온다.

### 4. 실무에서 많이 쓴다
스프링부트는 자바 백엔드 개발에서 매우 흔하게 사용된다.

보통 아래 같은 서버를 만들 때 사용한다.

```
REST API 서버
관리자 페이지 서버
쇼핑몰 서버
로그인/회원 서버
게시판 서버
결제 서버
사내 업무 시스템
```

스프링부트를 잘 이해하면 자바 백엔드 구조를 이해하는 데 큰 도움이 된다.

---

### 29. Starter 의존성 이해
<a id="index29"></a>

스프링부트에서 `starter`는 관련 라이브러리 묶음이다.

`build.gradle.kts`에 아래 같은 의존성을 넣으면 웹 개발에 필요한 기본 라이브러리들이 함께 들어온다.

```kotlin
implementation("org.springframework.boot:spring-boot-starter-webmvc")
```

이 의존성 하나에는 웹 요청 처리에 필요한 여러 라이브러리가 포함된다.

```
Spring MVC
↓
Jackson
↓
내장 Tomcat
↓
기본 로깅
↓
HTTP 메시지 변환기
```

### 자주 쓰는 Starter
```
spring-boot-starter-webmvc
→ 전통적인 Spring MVC 기반 웹 API 개발

spring-boot-starter-webflux
→ 비동기/논블로킹 웹 개발

spring-boot-starter-data-jpa
→ JPA로 DB 접근

spring-boot-starter-jdbc
→ JDBC로 DB 접근

spring-boot-starter-security
→ 로그인, 인증, 권한 같은 보안 기능

spring-boot-starter-validation
→ @Valid, @NotBlank 같은 요청 검증 기능

spring-boot-starter-test
→ 테스트 코드 작성에 필요한 기능
```

### Starter를 쓰는 이유
Starter를 쓰면 라이브러리 버전 충돌을 줄일 수 있다.

직접 라이브러리를 하나씩 넣으면 아래 문제가 생길 수 있다.

```
A 라이브러리는 Jackson 2.17 필요
↓
B 라이브러리는 Jackson 2.15 필요
↓
버전 충돌 가능
```

스프링부트는 특정 버전에 맞는 라이브러리 조합을 관리해준다.
그래서 보통은 Starter를 중심으로 의존성을 추가하는 것이 편하다.

### 의존성을 많이 넣으면 좋은가?
아니다.

필요하지 않은 의존성을 너무 많이 넣으면 자동 설정이 많이 켜지고, 애플리케이션 실행이 복잡해질 수 있다.

예를 들어 JDBC, MySQL, Session JDBC 의존성을 넣어두면 스프링부트는 DB가 필요하다고 판단할 수 있다.
그러면 DataSource 설정이 없을 때 실행 또는 테스트가 실패할 수 있다.

즉, 의존성은 필요한 것만 넣는 것이 좋다.

---

### 30. 자동 설정과 내장 서버 이해
<a id="index30"></a>

### 자동 설정이란?
자동 설정은 스프링부트가 현재 프로젝트 상태를 보고 필요한 Bean과 설정을 자동으로 등록하는 기능이다.

예를 들어 프로젝트에 웹 관련 의존성이 있으면 스프링부트는 아래처럼 판단한다.

```
웹 라이브러리가 있네?
↓
그럼 웹 서버가 필요하겠네
↓
내장 톰캣 준비
↓
Spring MVC 설정
↓
JSON 변환기 설정
↓
Controller 요청 매핑 준비
```

반대로 DB 관련 의존성이 있으면 아래처럼 판단할 수 있다.

```
JDBC 라이브러리가 있네?
↓
DB 연결이 필요하겠네
↓
DataSource Bean을 만들어야겠네
↓
application.yaml에서 DB 설정을 찾아야지
```

그래서 의존성은 자동 설정과 연결된다.
어떤 의존성을 넣느냐에 따라 스프링부트가 자동으로 준비하는 기능도 달라진다.

### 내장 서버란?
스프링부트는 서버를 애플리케이션 안에 포함해서 실행한다.

대표적으로 Tomcat이 많이 사용된다.

예전 방식은 아래와 비슷했다.

```
톰캣 설치
↓
WAR 파일 생성
↓
톰캣에 배포
↓
서버 실행
```

스프링부트 방식은 더 간단하다.

```
스프링부트 애플리케이션 실행
↓
내장 톰캣도 같이 실행
↓
바로 HTTP 요청 받을 수 있음
```

그래서 `Application.java`를 실행하면 서버도 같이 뜬다.

```java
public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
}
```

### 요청이 들어오면 내부에서 일어나는 일
```
브라우저가 HTTP 요청
↓
내장 톰캣이 요청 받음
↓
DispatcherServlet으로 전달
↓
어떤 Controller가 처리할지 찾음
↓
Controller 메서드 실행
↓
Service 호출
↓
Repository 호출
↓
응답 객체 반환
↓
JSON으로 변환
↓
HTTP 응답 전송
```

여기서 `DispatcherServlet`은 스프링 MVC의 핵심 입구다.
모든 웹 요청을 받아서 알맞은 Controller로 보내주는 역할을 한다.

---

### 31. Gradle과 빌드 기본
<a id="index31"></a>

현재 프로젝트는 Gradle을 사용한다.

Gradle은 자바 프로젝트를 빌드하고 실행하고 테스트하는 도구다.

### `build.gradle.kts`란?
`build.gradle.kts`는 프로젝트 빌드 설정 파일이다. [[ 파일 찾아가기 ]](./build.gradle.kts)

이 파일에는 보통 아래 내용이 들어간다.

```
플러그인 설정
↓
프로젝트 그룹과 버전
↓
자바 버전
↓
의존성 목록
↓
테스트 설정
↓
빌드 작업 설정
```

예시:

```kotlin
plugins {
    java
    id("org.springframework.boot") version "4.0.6"
}
```

`org.springframework.boot` 플러그인은 스프링부트 프로젝트를 빌드하고 실행하는 기능을 제공한다.

### `settings.gradle.kts`란?
`settings.gradle.kts`는 Gradle 프로젝트 이름 같은 기본 설정을 담는다. [[ 파일 찾아가기 ]](./settings.gradle.kts)

```kotlin
rootProject.name = "SpringBoot"
```

### 자주 쓰는 Gradle 명령어
윈도우 PowerShell 기준으로 아래처럼 실행한다.

```powershell
.\gradlew.bat bootRun
```

스프링부트 서버를 실행한다.

```powershell
.\gradlew.bat compileJava
```

자바 코드가 컴파일되는지 확인한다.

```powershell
.\gradlew.bat test
```

테스트 코드를 실행한다.

```powershell
.\gradlew.bat build
```

컴파일, 테스트, jar 생성까지 전체 빌드를 수행한다.

### Gradle Wrapper란?
프로젝트에는 아래 파일들이 있다.

```
gradlew
gradlew.bat
gradle/wrapper
```

이것을 Gradle Wrapper라고 한다.

Gradle Wrapper를 사용하면 내 컴퓨터에 Gradle을 직접 설치하지 않아도,
프로젝트가 지정한 Gradle 버전으로 빌드할 수 있다.

그래서 보통은 `gradle build`보다 아래 명령어를 사용한다.

```powershell
.\gradlew.bat build
```

---

### 32. 스프링부트 개발 순서 기본
<a id="index32"></a>

처음 스프링부트를 공부할 때는 어떤 순서로 코드를 작성해야 하는지 헷갈릴 수 있다.
보통은 아래 순서로 생각하면 된다.

### 1. 어떤 API를 만들지 정한다
예를 들어 유저 생성 API를 만든다고 해보자.

```
POST /users
```

요청 데이터:

```json
{
  "name": "kim",
  "age": 20
}
```

응답 데이터:

```json
{
  "id": 1,
  "name": "kim",
  "age": 20
}
```

먼저 URL, Method, 요청 Body, 응답 Body를 정하면 구현 방향이 훨씬 명확해진다.

### 2. DTO를 만든다
요청과 응답에 사용할 DTO를 만든다.

```
UserCreateRequest
→ 요청 JSON을 받을 객체

UserResponse
→ 응답 JSON으로 보낼 객체
```

DTO를 먼저 만들면 Controller 메서드 모양이 잡힌다.

### 3. Controller를 만든다
Controller는 요청 주소와 자바 메서드를 연결한다.

```java
@PostMapping("/users")
public UserResponse createUser(@RequestBody UserCreateRequest request) {
    return userService.createUser(request);
}
```

Controller는 최대한 얇게 유지한다.
요청을 받고 Service를 호출한 뒤 결과를 반환하는 역할에 집중한다.

### 4. Service를 만든다
Service에는 실제 기능 흐름을 작성한다.

```
요청 값 확인
↓
중복 검사
↓
Entity 생성
↓
Repository 저장 요청
↓
Response DTO로 변환
```

중요한 비즈니스 규칙은 Service에 두는 것이 좋다.

### 5. Repository를 만든다
Repository는 데이터를 저장하거나 조회한다.

처음 공부할 때는 메모리 `Map`으로 시작해도 된다.
DB를 배우기 시작하면 JPA Repository나 JDBC Repository로 바꿔볼 수 있다.

### 6. 실행해서 API를 테스트한다
서버를 실행한다.

```powershell
.\gradlew.bat bootRun
```

브라우저 또는 PowerShell로 호출한다.

```powershell
Invoke-RestMethod `
  -Method Post `
  -Uri "http://localhost:8080/example/users" `
  -ContentType "application/json" `
  -Body '{"name":"kim","age":20}'
```

### 7. 에러 상황도 테스트한다
정상 요청만 테스트하면 부족하다.

아래 같은 경우도 확인해야 한다.

```
name이 비어 있으면?
age가 음수면?
없는 id를 조회하면?
JSON 형식이 틀리면?
HTTP Method를 잘못 보내면?
```

좋은 API는 정상 상황뿐 아니라 잘못된 요청에도 예측 가능한 응답을 준다.

### 초보자가 기억할 기본 순서
```
API 설계
↓
DTO 작성
↓
Controller 작성
↓
Service 작성
↓
Repository 작성
↓
실행
↓
테스트
↓
에러 처리
```

처음부터 모든 기능을 완벽하게 만들려고 하기보다,
작은 API 하나가 Controller, Service, Repository를 어떤 순서로 지나가는지 반복해서 익히는 것이 중요하다.
