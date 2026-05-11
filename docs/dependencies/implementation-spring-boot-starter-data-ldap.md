# implementation - spring-boot-starter-data-ldap

## Gradle 선언

~~~kotlin
implementation("org.springframework.boot:spring-boot-starter-data-ldap")
~~~

## 이 의존성이 하는 일

Spring Data 방식으로 LDAP 디렉터리 데이터를 조회하기 위한 의존성이다.

## 언제 쓰는가

해당 기능이 실제 애플리케이션 코드 또는 개발 환경에 필요할 때 쓴다.

## 핵심 개념 더 자세히

이 의존성은 특정 기능을 구현할 때 필요한 API, 자동 설정, 런타임 라이브러리를 제공한다. 스프링부트 Starter는 보통 관련 라이브러리 여러 개를 묶어서 가져오므로, 하나를 추가해도 자동 설정 범위가 넓어질 수 있다.

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

### LDAP 연결 설정 예시

~~~yaml
spring:
  ldap:
    urls: ldap://localhost:8389
    base: dc=example,dc=com
    username: cn=admin,dc=example,dc=com
    password: secret
~~~

## 기본 예제 코드

아래 코드는 사용 형태를 보여주는 예시다. 실제 프로젝트에서는 패키지명, 클래스명, 설정 값을 현재 구조에 맞게 바꿔야 한다.

### 예제

~~~java
import javax.naming.Name;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(base = "ou=people", objectClasses = {"person"})
public class LdapUser {
    @Id
    private Name id;
    private String cn;
}

public interface LdapUserRepository extends LdapRepository<LdapUser> {
}
~~~

## 추가 예제

### Service 계층 사용 예시

~~~java
@Service
public class ExampleService {
    public String execute() {
        return "dependency usage example";
    }
}
~~~
### Controller 연결 예시

~~~java
@RestController
public class ExampleController {
    @GetMapping("/example")
    public String example() {
        return "ok";
    }
}
~~~

## 주의점

예제 코드는 사용 형태를 보여주기 위한 최소 예시이므로 실제 프로젝트 구조에 맞게 수정해야 한다.

## 확인 방법

애플리케이션 시작 로그에서 연결 오류가 없는지 보고, 간단한 조회 API나 테스트를 만들어 실제 저장소에 접근되는지 확인한다.

## 자주 생기는 실수

의존성만 추가하면 기능이 자동으로 완성된다고 생각하기 쉽다. 대부분은 관련 설정, Controller, Service, 테스트 코드가 함께 있어야 실제 기능이 된다.

## 같이 보면 좋은 것

- group: org.springframework.boot
- artifact: spring-boot-starter-data-ldap
- configuration: implementation
- 원본 파일: build.gradle.kts
