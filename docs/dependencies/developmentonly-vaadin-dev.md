# developmentOnly - vaadin-dev

## Gradle 선언

~~~kotlin
developmentOnly("com.vaadin:vaadin-dev")
~~~

## 이게 뭐하는 건데?

자바 코드로 웹 UI를 만드는 Vaadin 관련 의존성이다.

## 언제 쓰면 되냐면

운영 배포에는 넣지 않고 개발 중 편의 기능으로만 사용하고 싶을 때 쓴다.

## 조금 더 풀어서 보면

Vaadin은 프론트엔드 코드를 직접 많이 작성하지 않고 자바 코드로 UI 컴포넌트를 구성하는 서버 중심 UI 프레임워크다. REST API 서버라기보다 관리 화면이나 사내 도구처럼 서버와 UI를 한 프로젝트에서 다루는 경우에 잘 맞는다.

## 동작 흐름

~~~text
의존성 추가
↓
자동 설정 또는 API 사용
↓
코드 작성
↓
실행/테스트
~~~

## 설정은 이런 식으로 함

모든 의존성이 설정을 꼭 요구하는 건 아니지만, 실제 프로젝트에서는 아래처럼 관련 설정이나 사용 방향을 같이 잡는 경우가 많다.

### 설정 방향

~~~text
이 의존성은 별도 설정 없이도 자동 설정으로 일부 기능이 켜질 수 있다.
다만 실제 기능을 쓰려면 Controller, Service, Repository, 설정 클래스 중 하나에서 관련 API를 직접 사용해야 한다.
~~~

## 기본 예제 코드

아래 코드는 이런 식으로 쓰는구나 정도로 보면 된다. 실제 프로젝트에서는 패키지명, 클래스명, 설정 값은 현재 구조에 맞게 바꿔야 한다.

### 예제

~~~java
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("hello-vaadin")
public class HelloVaadinView extends VerticalLayout {
    public HelloVaadinView() {
        add(new Button("Click me", event -> add("clicked")));
    }
}
~~~

## 추가로 보면 좋은 예시

### Vaadin 입력 폼 예시

~~~java
TextField name = new TextField("name");
Button save = new Button("save");
add(name, save);
~~~
### Vaadin Grid 예시

~~~java
Grid<UserResponse> grid = new Grid<>(UserResponse.class);
grid.setItems(userService.findUsers());
add(grid);
~~~

## 주의할 점

예제 코드는 사용 형태를 보여주기 위한 최소 예시이므로 실제 프로젝트 구조에 맞게 수정해야 한다.

## 제대로 되는지 확인하는 법

.\gradlew.bat compileJava로 컴파일을 확인하고, 해당 기능을 쓰는 최소 API나 테스트를 만들어 동작 여부를 검증한다.

## 자주 하는 실수

의존성만 추가하면 기능이 자동으로 완성된다고 생각하기 쉽다. 대부분은 관련 설정, Controller, Service, 테스트 코드가 함께 있어야 실제 기능이 된다.

## 같이 보면 좋은 정보

- group: com.vaadin
- artifact: vaadin-dev
- configuration: developmentOnly
- 원본 파일: build.gradle.kts
