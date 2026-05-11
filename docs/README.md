# build.gradle.kts 의존성 해설 문서

이 폴더는 프로젝트 루트의 `build.gradle.kts`에 들어 있는 플러그인, BOM, 의존성, 테스트 의존성을 하나씩 풀어서 설명하기 위한 문서 모음이다.

## 문서 목록

1. [빌드 설정 전체 구조](./build-gradle-overview.md)
   - Gradle 플러그인
   - Java 버전
   - Repository
   - BOM
   - Gradle task 설정

2. [main 구현 의존성](./main-dependencies.md)
   - `implementation(...)` 중심 설명
   - 웹, DB, 보안, GraphQL, Vaadin, Modulith 관련 의존성

3. [개발/런타임/컴파일 보조 의존성](./development-runtime-processor-dependencies.md)
   - `developmentOnly(...)`
   - `compileOnly(...)`
   - `runtimeOnly(...)`
   - `annotationProcessor(...)`

4. [테스트 의존성](./test-dependencies.md)
   - `testImplementation(...)`
   - `testCompileOnly(...)`
   - `testRuntimeOnly(...)`
   - `testAnnotationProcessor(...)`

## Gradle 의존성 범위 기본

`implementation`
→ 애플리케이션 실행과 컴파일에 필요한 일반 의존성이다.

`developmentOnly`
→ 개발할 때만 필요한 의존성이다. 운영 jar에는 보통 포함하지 않는다.

`compileOnly`
→ 컴파일할 때만 필요하고 실행 시에는 필요 없는 의존성이다.

`runtimeOnly`
→ 컴파일에는 직접 필요 없지만 실행할 때 필요한 의존성이다.

`annotationProcessor`
→ 컴파일 시점에 어노테이션을 읽고 코드를 생성하거나 메타데이터를 만드는 도구다.

`testImplementation`
→ 테스트 코드에서 사용하는 의존성이다.

`testRuntimeOnly`
→ 테스트 실행 시점에만 필요한 의존성이다.

## 현재 build.gradle.kts의 특징

현재 프로젝트는 학습용으로 매우 많은 Starter가 들어 있다.
덕분에 스프링부트의 여러 기능을 한 번에 볼 수 있지만, 실제 서비스 프로젝트라면 필요한 의존성만 남기는 편이 좋다.

특히 JDBC, Session JDBC, MySQL 관련 의존성이 있으므로 DataSource 설정이 없으면 애플리케이션 컨텍스트 실행이나 테스트에서 DB 설정 오류가 날 수 있다.
