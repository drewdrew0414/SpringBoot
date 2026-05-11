# 개발/런타임/컴파일 보조 의존성

이 문서는 `implementation` 말고 main 쪽에서 같이 쓰이는 보조 의존성을 설명하는 문서다.

포함되는 범위는 아래와 같음.

```
developmentOnly
compileOnly
runtimeOnly
annotationProcessor
```

## 1. `com.vaadin:vaadin-dev`

```kotlin
developmentOnly("com.vaadin:vaadin-dev")
```

Vaadin 개발 모드에 필요한 의존성이다.

`developmentOnly`이므로 개발 환경에서만 필요하다.
운영 실행 파일에는 보통 포함하지 않는다.

하는 일은 아래와 같음.

```
Vaadin 개발 모드 지원
↓
프론트엔드 리소스 처리
↓
개발 중 빠른 화면 반영 지원
```

현재 프로젝트에 `com.vaadin:vaadin-spring-boot-starter`와 `com.vaadin` Gradle 플러그인이 같이 있으므로, Vaadin 개발 환경 보조 역할을 한다.

Vaadin 화면을 만들지 않는다면 필요성이 낮다.

## 2. `org.projectlombok:lombok`

```kotlin
compileOnly("org.projectlombok:lombok")
```

Lombok은 반복적인 자바 코드를 줄여주는 라이브러리다.

예를 들어 아래 코드를:

```java
public class User {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

Lombok을 사용하면 아래처럼 줄일 수 있다.

```java
@Getter
public class User {
    private String name;
    private int age;
}
```

`compileOnly`로 들어가는 이유:

```
컴파일 시점에 코드 생성 필요
↓
실행 시점에는 Lombok 라이브러리가 직접 필요하지 않음
```

주의할 점:

Lombok은 코드를 짧게 만들어주지만, 처음 배우는 단계에서는 getter, 생성자, setter를 직접 작성해보는 것이 자바 기본 이해에 도움이 된다.

## 3. `org.springframework.boot:spring-boot-devtools`

```kotlin
developmentOnly("org.springframework.boot:spring-boot-devtools")
```

스프링부트 개발 편의 기능을 제공한다.

대표 기능:

```
코드 변경 시 자동 재시작
↓
개발용 캐시 비활성화
↓
LiveReload 지원
```

`developmentOnly`로 들어가는 이유:

```
개발할 때만 필요
↓
운영 환경에는 불필요
↓
운영 jar에 포함하지 않는 것이 일반적
```

개발 중 Controller나 Service 코드를 수정한 뒤 서버를 수동으로 껐다 켜는 일을 줄여준다.

## 4. `org.springframework.boot:spring-boot-docker-compose`

```kotlin
developmentOnly("org.springframework.boot:spring-boot-docker-compose")
```

스프링부트가 `compose.yaml`을 감지해서 개발용 Docker Compose 서비스를 연동할 수 있게 도와준다.

현재 프로젝트에는 `compose.yaml`이 있고 MySQL, Redis 서비스가 정의되어 있다.

```yaml
services:
  mysql:
    image: 'mysql:latest'
  redis:
    image: 'redis:latest'
```

의미는 아래와 같음.

```
애플리케이션 시작
↓
Docker Compose 서비스 감지
↓
MySQL, Redis 같은 개발 인프라와 연동
```

주의할 점:

Docker Desktop 또는 Docker 환경이 필요하다.
Docker가 없거나 Compose 서비스가 제대로 뜨지 않으면 관련 자동 연동이 실패할 수 있다.

## 5. `com.mysql:mysql-connector-j`

```kotlin
runtimeOnly("com.mysql:mysql-connector-j")
```

MySQL JDBC 드라이버다.

자바 애플리케이션이 MySQL과 통신하려면 JDBC 드라이버가 필요하다.

`runtimeOnly`로 들어가는 이유:

```
컴파일할 때는 직접 MySQL Driver 클래스를 사용하지 않음
↓
실행 중 DataSource가 MySQL에 연결할 때 필요
```

필요한 설정 예시:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydatabase
    username: myuser
    password: secret
```

현재 프로젝트의 `compose.yaml`에는 MySQL 설정이 있으므로, Docker Compose와 함께 사용하면 로컬 DB를 띄울 수 있다.

## 6. `org.springframework.boot:spring-boot-configuration-processor`

```kotlin
annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
```

`@ConfigurationProperties`를 사용할 때 설정 메타데이터를 생성해주는 어노테이션 프로세서다.

예시:

```java
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String uploadPath;
}
```

그리고 `application.yaml`에 아래처럼 설정한다고 해보자.

```yaml
app:
  upload-path: /tmp/uploads
```

configuration processor가 있으면 IDE에서 자동완성, 문서화, 타입 힌트 같은 도움을 받을 수 있다.

하는 일은 아래와 같음.

```
@ConfigurationProperties 클래스 분석
↓
설정 메타데이터 생성
↓
IDE 자동완성과 설정 검증 보조
```

실행 로직 자체보다 개발 편의성과 설정 안정성에 도움을 준다.

## 7. `org.projectlombok:lombok`

```kotlin
annotationProcessor("org.projectlombok:lombok")
```

Lombok 어노테이션을 실제 코드로 변환하기 위한 어노테이션 프로세서다.

`compileOnly("org.projectlombok:lombok")`만 있으면 Lombok 어노테이션 타입은 컴파일러가 알 수 있다.
하지만 실제로 getter, setter, 생성자 같은 코드를 생성하려면 annotation processor도 필요하다.

흐름:

```
@Getter 발견
↓
Lombok annotation processor 실행
↓
getName(), getAge() 같은 메서드 생성
↓
컴파일 완료
```

Lombok을 사용할 때는 보통 아래 두 줄이 같이 들어간다.

```kotlin
compileOnly("org.projectlombok:lombok")
annotationProcessor("org.projectlombok:lombok")
```

테스트 코드에서 Lombok을 쓰려면 test용 Lombok 설정도 따로 필요하다.
