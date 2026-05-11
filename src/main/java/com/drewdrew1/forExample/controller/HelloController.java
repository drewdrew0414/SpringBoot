package com.drewdrew1.forExample.controller;

import com.drewdrew1.forExample.dto.UserCreateRequest;
import com.drewdrew1.forExample.dto.UserResponse;
import com.drewdrew1.forExample.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

/*
 * Controller는 클라이언트의 HTTP 요청을 가장 먼저 받는 계층이다.
 *
 * 이 클래스에서는 일부러 이름을 HelloController로 유지했다.
 * 기존 README에서 사용하던 간단한 hello 예제와,
 * Controller -> Service -> Repository 흐름을 보여주는 users 예제를 같이 담기 위해서다.
 */
@RestController
@RequestMapping("/example")
public class HelloController {

    /*
     * Controller가 직접 UserRepository를 호출하지 않고 UserService를 호출한다.
     *
     * 이유:
     * - Controller는 HTTP 요청/응답에 집중하는 것이 좋다.
     * - 실제 기능 흐름, 검증, 저장 규칙은 Service가 담당하는 것이 좋다.
     * - 이렇게 나누면 나중에 코드가 커져도 역할이 명확하다.
     */
    private final UserService userService;

    /*
     * 생성자 주입 방식이다.
     *
     * 스프링은 HelloController Bean을 만들 때 생성자를 확인한다.
     * 생성자에 UserService가 필요하다는 것을 보고,
     * 스프링 컨테이너에 등록된 UserService Bean을 자동으로 넣어준다.
     */
    public HelloController(UserService userService) {
        this.userService = userService;
    }

    /*
     * GET /example/hello 요청을 처리한다.
     *
     * 브라우저에서 http://localhost:8080/example/hello 로 접속하면
     * 이 메서드가 실행되고, 문자열이 그대로 응답으로 나간다.
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello spring";
    }

    /*
     * GET /example/users 요청을 처리한다.
     *
     * 이 메서드는 유저 전체 목록을 조회한다.
     * 실제 조회 작업은 Service에게 맡기고,
     * Controller는 Service가 돌려준 결과를 그대로 응답한다.
     */
    @GetMapping("/users")
    public List<UserResponse> findUsers() {
        return userService.findUsers();
    }

    /*
     * GET /example/users/{id} 요청을 처리한다.
     *
     * {id}는 URL 경로에 들어오는 값을 의미한다.
     * 예를 들어 /example/users/1 로 요청하면 id 값은 1이 된다.
     *
     * @PathVariable은 URL 경로 값을 자바 변수로 꺼내주는 어노테이션이다.
     */
    @GetMapping("/users/{id}")
    public UserResponse findUser(@PathVariable Long id) {
        return userService.findUser(id);
    }

    /*
     * POST /example/users 요청을 처리한다.
     *
     * 클라이언트가 아래 같은 JSON을 보내면
     * {
     *   "name": "park",
     *   "age": 30
     * }
     *
     * @RequestBody가 JSON을 UserCreateRequest 객체로 변환해준다.
     */
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }
}
