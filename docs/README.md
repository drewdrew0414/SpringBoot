# build.gradle.kts 의존성 해설 문서

이 폴더는 `build.gradle.kts`에 들어 있는 내용을 하나씩 뜯어보는 공간이다.

README가 스프링부트 전체 흐름을 설명하는 문서라면, 여기 있는 문서들은 "이 의존성이 왜 들어갔고, 어디에 쓰이고, 어떤 예제 코드로 확인할 수 있는지"를 보는 문서라고 생각하면 된다.

## 문서 목록

### 0. 의존성별 사용 예제 문서
[[ 파일 찾아가기 ]](./dependencies/README.md)

`build.gradle.kts`에 선언된 의존성을 1개 파일당 1개씩 나눠서 설명해둔 문서다.

```
Gradle 선언
↓
무슨 기능인지
↓
언제 쓰는지
↓
설정 예시
↓
예제 코드
↓
주의할 점
```

### 1. 빌드 설정 전체 구조
[[ 파일 찾아가기 ]](./build-gradle-overview.md)

Gradle 플러그인, Java 버전, Repository, BOM, Gradle task 설정을 설명한다.

### 2. main 구현 의존성
[[ 파일 찾아가기 ]](./main-dependencies.md)

`implementation(...)` 중심으로 웹, DB, 보안, GraphQL, Vaadin, Modulith 관련 의존성을 설명한다.

### 3. 개발/런타임/컴파일 보조 의존성
[[ 파일 찾아가기 ]](./development-runtime-processor-dependencies.md)

`developmentOnly`, `compileOnly`, `runtimeOnly`, `annotationProcessor`가 각각 어떤 역할인지 설명한다.

### 4. 테스트 의존성
[[ 파일 찾아가기 ]](./test-dependencies.md)

`testImplementation`, `testCompileOnly`, `testRuntimeOnly`, `testAnnotationProcessor`가 테스트에서 어떻게 쓰이는지 설명한다.

## Gradle 의존성 범위 기본

Gradle 의존성은 그냥 아무 데나 넣는 것이 아니라, 언제 필요한지에 따라 위치가 나뉜다.

```
implementation
→ 애플리케이션 실행과 컴파일에 필요한 일반 의존성

developmentOnly
→ 개발할 때만 필요한 의존성

compileOnly
→ 컴파일할 때만 필요하고 실행 시에는 필요 없는 의존성

runtimeOnly
→ 컴파일에는 직접 필요 없지만 실행할 때 필요한 의존성

annotationProcessor
→ 컴파일 시점에 어노테이션을 읽고 코드를 생성하는 도구

testImplementation
→ 테스트 코드에서 사용하는 의존성

testRuntimeOnly
→ 테스트 실행 시점에만 필요한 의존성
```

## 현재 build.gradle.kts를 볼 때 주의할 점

현재 프로젝트는 학습용으로 여러 Starter가 많이 들어 있다.

덕분에 스프링부트의 다양한 기능을 한 번에 볼 수 있지만, 실제 서비스 프로젝트라면 필요한 의존성만 남기는 편이 좋다.

특히 JDBC, Session JDBC, MySQL 관련 의존성이 있으므로 DataSource 설정이 없으면 애플리케이션 컨텍스트 실행이나 테스트에서 DB 설정 오류가 날 수 있다.
