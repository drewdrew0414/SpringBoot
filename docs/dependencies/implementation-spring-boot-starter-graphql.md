# implementation - spring-boot-starter-graphql

## Gradle 선언

~~~kotlin
implementation("org.springframework.boot:spring-boot-starter-graphql")
~~~

## 이 의존성이 하는 일

GraphQL API를 만들거나 테스트하기 위한 의존성이다.

## 언제 쓰는가

해당 기능이 실제 애플리케이션 코드 또는 개발 환경에 필요할 때 쓴다.

## 핵심 개념 더 자세히

GraphQL은 URL을 여러 개로 나누기보다 하나의 endpoint에 query를 보내 필요한 필드를 선택하는 방식이다. REST의 endpoint 중심 사고와 다르게 schema와 type 중심으로 API를 설계한다.

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

### GraphQL schema 예시

~~~graphql
type Query {
  hello: String
  user(id: ID!): User
}

type User {
  id: ID!
  name: String!
}
~~~

## 기본 예제 코드

아래 코드는 사용 형태를 보여주는 예시다. 실제 프로젝트에서는 패키지명, 클래스명, 설정 값을 현재 구조에 맞게 바꿔야 한다.

### 예제

~~~java
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphqlController {
    @QueryMapping
    public String hello() {
        return "hello graphql";
    }
}
~~~

## 추가 예제

### GraphQL Query 예시

~~~graphql
query {
  user(id: 1) {
    id
    name
  }
}
~~~
### DGS field resolver 예시

~~~java
@DgsData(parentType = "User", field = "name")
public String userName(DgsDataFetchingEnvironment env) {
    return "kim";
}
~~~

## 주의점

예제 코드는 사용 형태를 보여주기 위한 최소 예시이므로 실제 프로젝트 구조에 맞게 수정해야 한다.

## 확인 방법

애플리케이션을 실행한 뒤 브라우저, Postman, PowerShell Invoke-RestMethod로 endpoint를 호출해서 상태 코드와 응답 Body를 확인한다.

## 자주 생기는 실수

schema의 필드 이름과 자바 resolver 메서드 이름이 맞지 않으면 요청이 실패한다. schema-first 방식에서는 schema가 API 계약이라는 점을 먼저 확인해야 한다.

## 같이 보면 좋은 것

- group: org.springframework.boot
- artifact: spring-boot-starter-graphql
- configuration: implementation
- 원본 파일: build.gradle.kts
