### 1. 인터넷에서 웹사이트가 열리는 과정

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

## 1. 프로젝트 구조 이해
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
1. [스프링부트 실행 구조 이해](#index1)
2. [스프링 컨테이너 및 Bean의 이해](#index2)
3. [Controller의 이해](#index3)
4. [Service 계층 이해](#index4)
5. [Repository의 이해](#index5)
6. [Entity 이해](#index6)
7. [DTO 이해](#index7)
8. [요청 데이터 받기](#index8)
9. [응답 데이터 만들기](#index9)
10. [application.yaml 이해](#index10)
11. [예시 코드 실행 흐름](#index11)
12. [API 테스트 방법](#index12)
13. [처음 배울 때 자주 헷갈리는 부분](#index13)

---

### 1. 스프링부트 실행 구조 이해
<a id="index1"></a>
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

<a id="index2"></a>
### 2-1. 스프링 컨테이너란?
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

### 2-2. Bean이란?
Bean은 스프링 컨테이너가 관리하는 객체를 말한다.

```java
@Service
public class UserService {
}
```

위 클래스는 `@Service`가 붙어 있으므로 스프링이 자동으로 객체를 생성한다.
그리고 그 객체를 컨테이너 안에 보관한다.
그렇게 보관된 객체를 Bean이라고 부른다.

### 2-3. 컴포넌트 스캔 흐름
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

### 2-4. DI(의존성 주입)란?
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

### 3. Controller의 이해
<a id="index3"></a>

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

### 4. Service 계층 이해
<a id="index4"></a>
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

### 5. Repository의 이해
<a id="index5"></a>
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

### 5-1. JPA란?
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

### 6. Entity 이해
<a id="index6"></a>
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

### 7. DTO 이해
<a id="index7"></a>
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

### 8. 요청 데이터 받기
<a id="index8"></a>
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

### 9. 응답 데이터 만들기
<a id="index9"></a>
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

### 10. application.yaml 이해
<a id="index10"></a>
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

### 11. 예시 코드 실행 흐름
<a id="index11"></a>
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

---

### 12. API 테스트 방법
<a id="index12"></a>
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

### 13. 처음 배울 때 자주 헷갈리는 부분
<a id="index13"></a>

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
