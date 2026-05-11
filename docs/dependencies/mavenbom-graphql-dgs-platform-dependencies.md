# mavenBom - graphql-dgs-platform-dependencies

## Gradle 선언

~~~kotlin
mavenBom("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:${property("netflixDgsVersion")}")
~~~

## 이게 뭐하는 건데?

이 BOM은 graphql-dgs-platform-dependencies 관련 라이브러리들의 버전을 한 번에 맞추기 위한 의존성 관리 설정이다.

## 언제 쓰면 되냐면

여러 관련 라이브러리의 버전을 직접 하나씩 적지 않고, 호환되는 버전 조합으로 관리하고 싶을 때 쓴다.

## 조금 더 풀어서 보면

BOM은 Bill Of Materials의 줄임말이다. 라이브러리 자체 기능을 제공하기보다는, 관련 라이브러리들의 버전 표를 Gradle에 알려주는 역할을 한다.

예를 들어 같은 Vaadin 계열 모듈을 여러 개 사용할 때 모듈마다 버전을 직접 쓰면 서로 맞지 않는 조합이 생길 수 있다. BOM을 import하면 Gradle이 BOM에 적힌 권장 버전 조합을 사용하므로 버전 충돌 위험이 줄어든다.

## 동작 흐름

~~~text
BOM import
↓
관련 의존성 선언
↓
Gradle이 버전 조합 관리
~~~

## 설정은 이런 식으로 함

모든 의존성이 설정을 꼭 요구하는 건 아니지만, 실제 프로젝트에서는 아래처럼 관련 설정이나 사용 방향을 같이 잡는 경우가 많다.

### BOM import 예시

~~~kotlin
dependencyManagement {
    imports {
        mavenBom("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:11.1.0")
    }
}
~~~

## 기본 예제 코드

아래 코드는 이런 식으로 쓰는구나 정도로 보면 된다. 실제 프로젝트에서는 패키지명, 클래스명, 설정 값은 현재 구조에 맞게 바꿔야 한다.

### 예제

~~~kotlin
dependencyManagement {
    imports {
        mavenBom("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:11.1.0")
    }
}
~~~

## 추가로 보면 좋은 예시

### BOM 적용 후 버전 생략 예시

~~~kotlin
dependencies {
    implementation("com.vaadin:vaadin-spring-boot-starter")
    developmentOnly("com.vaadin:vaadin-dev")
}
~~~
### BOM 사용 흐름 예시

~~~text
BOM을 쓰면 위 의존성들에 버전을 직접 쓰지 않아도 된다.
버전은 dependencyManagement의 BOM이 결정한다.
~~~

## 주의할 점

BOM은 코드에서 import하는 라이브러리가 아니라 버전 관리를 위한 Gradle 설정이다.

## 제대로 되는지 확인하는 법

Gradle dependency insight나 build 로그에서 관련 라이브러리 버전이 BOM 버전 조합으로 맞춰졌는지 확인한다.

## 자주 하는 실수

BOM을 추가했는데도 의존성에 명시 버전을 섞어 쓰면 버전 관리 의도가 흐려질 수 있다. 특별한 이유가 없으면 BOM이 관리하는 버전을 따르는 편이 좋다.

## 같이 보면 좋은 정보

- group: com.netflix.graphql.dgs
- artifact: graphql-dgs-platform-dependencies
- configuration: mavenBom
- 원본 파일: build.gradle.kts
