# build.gradle.kts 전체 구조

이 문서는 `build.gradle.kts`에서 의존성 바깥에 있는 빌드 설정을 설명하는 문서다.
플러그인, Java 버전, Repository, BOM, Task 설정도 프로젝트 동작에 큰 영향을 준다.

## 1. plugins

### `java`

```kotlin
java
```

자바 프로젝트를 빌드하기 위한 기본 Gradle 플러그인이다.

하는 일은 아래와 같음.

```
src/main/java 컴파일
↓
src/test/java 테스트 컴파일
↓
jar 생성
↓
test task 제공
```

이 플러그인이 있어야 Gradle이 이 프로젝트를 자바 프로젝트로 인식한다.

### `org.springframework.boot` version `4.0.6`

```kotlin
id("org.springframework.boot") version "4.0.6"
```

스프링부트 프로젝트를 빌드하고 실행하기 위한 플러그인이다.

제공하는 대표 기능:

```
bootRun
→ 스프링부트 애플리케이션 실행

bootJar
→ 실행 가능한 jar 생성

스프링부트 의존성 관리 연동
→ Starter 버전 조합을 쉽게 맞춤
```

이 프로젝트에서 `.\gradlew.bat bootRun`을 사용할 수 있는 이유가 이 플러그인 때문이다.

### `io.spring.dependency-management` version `1.1.7`

```kotlin
id("io.spring.dependency-management") version "1.1.7"
```

Maven BOM 기반 의존성 버전 관리를 Gradle에서 편하게 쓰도록 도와주는 플러그인이다.

예를 들어 아래처럼 BOM을 import하면:

```kotlin
mavenBom("com.vaadin:vaadin-bom:${property("vaadinVersion")}")
```

Vaadin 관련 의존성 버전을 하나씩 직접 쓰지 않아도 BOM이 맞는 버전을 관리해준다.

### `com.netflix.dgs.codegen` version `8.3.0`

```kotlin
id("com.netflix.dgs.codegen") version "8.3.0"
```

Netflix DGS GraphQL Codegen 플러그인이다.

GraphQL schema 파일을 읽고 자바 타입이나 GraphQL client 코드를 생성하는 데 사용한다.

현재 설정:

```kotlin
tasks.generateJava {
    schemaPaths.add("${projectDir}/src/main/resources/graphql-client")
    packageName = "com.drewdrew1.codegen"
    generateClient = true
}
```

의미는 아래와 같음.

```
src/main/resources/graphql-client 경로의 GraphQL schema 읽기
↓
com.drewdrew1.codegen 패키지에 코드 생성
↓
GraphQL client 코드 생성 활성화
```

GraphQL을 실제로 사용하지 않는다면 이 플러그인과 task 설정은 필요 없을 수 있다.

### `org.graalvm.buildtools.native` version `0.11.5`

```kotlin
id("org.graalvm.buildtools.native") version "0.11.5"
```

GraalVM Native Image 빌드를 위한 플러그인이다.

일반적인 JVM jar가 아니라, 운영체제에서 바로 실행할 수 있는 native 실행 파일을 만들 때 사용한다.

장점:

```
빠른 시작 시간
↓
낮은 메모리 사용량 가능
↓
컨테이너 환경에서 유리할 수 있음
```

주의할 점:

```
빌드 시간이 길 수 있음
↓
리플렉션, 프록시, 동적 로딩 설정이 필요할 수 있음
↓
처음 배우는 단계에서는 필수 아님
```

### `com.vaadin` version `25.1.5`

```kotlin
id("com.vaadin") version "25.1.5"
```

Vaadin 프론트엔드 빌드와 개발 서버 연동을 위한 플러그인이다.

Vaadin은 자바 코드 중심으로 웹 UI를 만드는 프레임워크다.
이 플러그인은 Vaadin 프론트엔드 리소스 준비, 번들링, 개발 모드 지원에 관여한다.

현재 프로젝트에 Vaadin Starter도 들어 있으므로 이 플러그인은 Vaadin 사용을 위한 빌드 지원 역할을 한다.

## 2. 프로젝트 기본 정보

```kotlin
group = "com.drewdrew1"
version = "0.0.1-SNAPSHOT"
description = "SpringBoot"
```

`group`
→ 프로젝트 패키지나 배포 좌표에서 조직 이름처럼 사용된다.

`version`
→ 현재 프로젝트 버전이다. `SNAPSHOT`은 아직 개발 중인 버전이라는 뜻으로 많이 쓴다.

`description`
→ 프로젝트 설명이다.

## 3. Java toolchain

```kotlin
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
```

이 프로젝트는 Java 21 기준으로 컴파일되도록 설정되어 있다.

의미는 아래와 같음.

```
자바 21 문법 사용 가능
↓
Gradle이 Java 21 toolchain 기준으로 컴파일
↓
개발자 PC마다 Java 버전 차이로 생기는 문제 감소
```

## 4. repositories

```kotlin
repositories {
    mavenCentral()
    maven { url = uri("https://build.shibboleth.net/maven/releases") }
}
```

### `mavenCentral()`

가장 많이 사용하는 공개 Maven 저장소다.
스프링부트, 스프링, Jackson, Lombok, MySQL Connector 같은 대부분의 라이브러리를 여기서 받는다.

### `https://build.shibboleth.net/maven/releases`

Shibboleth 관련 Maven 저장소다.
SAML2, 보안, 인증 관련 라이브러리 중 일부가 이 저장소를 필요로 할 수 있다.

현재 프로젝트에는 `spring-boot-starter-security-saml2`가 들어 있으므로 이 저장소가 추가된 것으로 볼 수 있다.

## 5. extra version 변수

```kotlin
extra["netflixDgsVersion"] = "11.1.0"
extra["springModulithVersion"] = "2.0.6"
extra["vaadinVersion"] = "25.1.5"
```

반복해서 쓰는 버전 값을 변수로 빼둔 것이다.

아래 BOM import에서 사용된다.

```kotlin
mavenBom("org.springframework.modulith:spring-modulith-bom:${property("springModulithVersion")}")
mavenBom("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:${property("netflixDgsVersion")}")
mavenBom("com.vaadin:vaadin-bom:${property("vaadinVersion")}")
```

버전 값을 한 곳에서 바꾸면 관련 BOM 버전이 같이 바뀐다.

## 6. dependencyManagement BOM

### `org.springframework.modulith:spring-modulith-bom`

Spring Modulith 관련 라이브러리들의 버전을 맞춰주는 BOM이다.

Modulith 의존성:

```kotlin
implementation("org.springframework.modulith:spring-modulith-starter-core")
implementation("org.springframework.modulith:spring-modulith-starter-jdbc")
testImplementation("org.springframework.modulith:spring-modulith-starter-test")
```

이 의존성들의 세부 버전을 일관되게 관리한다.

### `com.netflix.graphql.dgs:graphql-dgs-platform-dependencies`

Netflix DGS GraphQL 관련 라이브러리들의 버전을 맞춰주는 BOM이다.

DGS 의존성:

```kotlin
implementation("com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter")
testImplementation("com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter-test")
```

DGS starter와 DGS test starter의 버전 조합을 관리한다.

### `com.vaadin:vaadin-bom`

Vaadin 관련 라이브러리들의 버전을 맞춰주는 BOM이다.

Vaadin 의존성:

```kotlin
implementation("com.vaadin:vaadin-spring-boot-starter")
developmentOnly("com.vaadin:vaadin-dev")
```

Vaadin은 내부 모듈이 많기 때문에 BOM으로 버전을 맞추는 것이 중요하다.

## 7. tasks.generateJava

```kotlin
tasks.generateJava {
    schemaPaths.add("${projectDir}/src/main/resources/graphql-client")
    packageName = "com.drewdrew1.codegen"
    generateClient = true
}
```

DGS Codegen task 설정이다.

이 설정은 GraphQL schema를 기준으로 코드를 생성한다.

설정 의미는 아래와 같음.

```
schemaPaths
→ GraphQL schema 파일 위치

packageName
→ 생성될 자바 코드의 패키지 이름

generateClient
→ GraphQL client 코드 생성 여부
```

GraphQL schema 파일이 없거나 GraphQL client를 쓰지 않는다면 이 task는 실질적으로 필요 없을 수 있다.

## 8. tasks.withType<Test>

```kotlin
tasks.withType<Test> {
    useJUnitPlatform()
}
```

JUnit 5 기반 테스트를 실행하도록 설정한다.

JUnit 5 테스트는 보통 아래 어노테이션을 사용한다.

```java
@Test
void contextLoads() {
}
```

`useJUnitPlatform()`이 없으면 Gradle이 JUnit 5 테스트를 제대로 인식하지 못하는 경우가 있다.
