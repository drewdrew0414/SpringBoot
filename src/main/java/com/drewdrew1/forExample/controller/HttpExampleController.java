package com.drewdrew1.forExample.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/*
 * HTTP 관련 기능을 모아서 보여주는 예시 Controller다.
 *
 * HelloController가 Controller -> Service -> Repository 구조를 보여주는 예제라면,
 * 이 클래스는 HTTP 요청 자체에서 값을 꺼내는 방법을 보여주는 예제다.
 */
@RestController
@RequestMapping("/example/http")
public class HttpExampleController {

    /*
     * Query Parameter 예시다.
     *
     * 호출 예:
     * GET /example/http/query?keyword=spring&page=2
     *
     * URL에서 ? 뒤에 붙는 값을 Query Parameter라고 부른다.
     * @RequestParam은 Query Parameter를 자바 변수로 꺼내준다.
     */
    @GetMapping("/query")
    public Map<String, Object> queryParameter(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("keyword", keyword);
        response.put("page", page);
        response.put("description", "Query Parameter는 검색어, 페이지 번호, 정렬 조건처럼 조회 조건을 보낼 때 자주 사용한다.");

        return response;
    }

    /*
     * Path Variable 예시다.
     *
     * 호출 예:
     * GET /example/http/path/books/10
     *
     * /books/10 처럼 URL 경로 자체에 들어간 값을 꺼낼 때 @PathVariable을 사용한다.
     */
    @GetMapping("/path/{category}/{id}")
    public Map<String, Object> pathVariable(
            @PathVariable String category,
            @PathVariable Long id
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("category", category);
        response.put("id", id);
        response.put("description", "Path Variable은 특정 자원 1개를 식별할 때 자주 사용한다.");

        return response;
    }

    /*
     * Header 예시다.
     *
     * Header는 요청에 붙어오는 부가 정보다.
     * 예를 들면 브라우저 정보, 인증 토큰, Content-Type, Accept 같은 값이 Header에 들어간다.
     *
     * @RequestHeader는 요청 Header 값을 자바 변수로 꺼내준다.
     */
    @GetMapping("/headers")
    public Map<String, Object> requestHeader(
            @RequestHeader(value = "User-Agent", defaultValue = "unknown") String userAgent,
            @RequestHeader(value = "Accept", defaultValue = "unknown") String accept
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("userAgent", userAgent);
        response.put("accept", accept);
        response.put("description", "Header는 요청 본문 데이터가 아니라 요청에 대한 부가 설명이다.");

        return response;
    }

    /*
     * Cookie 예시다.
     *
     * Cookie는 브라우저가 서버에 자동으로 같이 보내는 작은 데이터다.
     * 로그인 세션 id, 사용자 설정값 같은 정보를 다룰 때 사용될 수 있다.
     *
     * @CookieValue는 요청 Cookie 값을 자바 변수로 꺼내준다.
     */
    @GetMapping("/cookies")
    public Map<String, Object> cookie(
            @CookieValue(value = "JSESSIONID", required = false) String sessionId
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("sessionId", sessionId == null ? "쿠키 없음" : sessionId);
        response.put("description", "Cookie는 브라우저가 같은 서버에 요청할 때 자동으로 함께 보낼 수 있는 값이다.");

        return response;
    }

    /*
     * HttpServletRequest 예시다.
     *
     * 스프링의 어노테이션으로 대부분의 값을 받을 수 있지만,
     * 요청의 원본 정보가 필요할 때는 HttpServletRequest를 파라미터로 받을 수도 있다.
     */
    @GetMapping("/request-info")
    public Map<String, Object> requestInfo(HttpServletRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("method", request.getMethod());
        response.put("requestUri", request.getRequestURI());
        response.put("remoteAddr", request.getRemoteAddr());
        response.put("description", "HttpServletRequest는 HTTP 요청 원본 정보에 접근할 수 있는 객체다.");

        return response;
    }

    /*
     * ResponseEntity 예시다.
     *
     * 일반적으로 객체를 바로 return하면 스프링이 200 OK로 응답한다.
     * 하지만 상태 코드, 응답 Header, 응답 Body를 직접 정하고 싶을 때는 ResponseEntity를 사용한다.
     */
    @GetMapping("/response-entity")
    public ResponseEntity<Map<String, Object>> responseEntity() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "ResponseEntity로 상태 코드, Header, Body를 직접 지정했다.");
        body.put("time", LocalDateTime.now().toString());

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("X-Example-Header", "spring-boot")
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    /*
     * @ResponseStatus 예시다.
     *
     * 메서드가 정상 실행됐을 때 특정 HTTP 상태 코드를 반환하고 싶다면
     * @ResponseStatus를 사용할 수 있다.
     */
    @PostMapping("/status")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> responseStatus() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", 201);
        response.put("message", "@ResponseStatus(HttpStatus.CREATED) 때문에 201 Created로 응답한다.");

        return response;
    }

    /*
     * 204 No Content 예시다.
     *
     * 삭제 요청처럼 성공은 했지만 응답 Body를 보낼 필요가 없을 때 204를 사용할 수 있다.
     */
    @GetMapping("/no-content")
    public ResponseEntity<Void> noContent() {
        return ResponseEntity
                .noContent()
                .header(HttpHeaders.CACHE_CONTROL, "no-store")
                .build();
    }
}
