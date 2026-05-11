package com.drewdrew1.forExample.dto;

/*
 * UserCreateRequest는 "요청 DTO"다.
 *
 * DTO는 Data Transfer Object의 약자이고,
 * 계층 사이 또는 클라이언트와 서버 사이에서 데이터를 옮길 때 사용하는 객체다.
 *
 * 이 클래스는 POST /example/users 요청에서 클라이언트가 보내는 JSON을 담기 위해 사용한다.
 */
public class UserCreateRequest {

    /*
     * 클라이언트가 보낸 JSON의 name 값이 들어온다.
     *
     * 예:
     * {
     *   "name": "kim"
     * }
     */
    private String name;

    /*
     * 클라이언트가 보낸 JSON의 age 값이 들어온다.
     *
     * int는 기본값이 0이다.
     * 그래서 age를 아예 보내지 않으면 0으로 들어올 수 있다.
     */
    private int age;

    /*
     * 기본 생성자다.
     *
     * Jackson은 JSON을 자바 객체로 바꿀 때 보통
     * 1. 기본 생성자로 객체를 만들고
     * 2. setter를 호출해서 값을 넣는다.
     *
     * 그래서 요청 DTO에는 기본 생성자를 만들어두는 경우가 많다.
     */
    public UserCreateRequest() {
    }

    /*
     * 테스트 코드나 직접 객체를 만들 때 편하게 쓰려고 만든 생성자다.
     * JSON 변환만 생각하면 필수는 아니다.
     */
    public UserCreateRequest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /*
     * getter는 객체 안에 들어있는 name 값을 밖에서 읽을 때 사용한다.
     */
    public String getName() {
        return name;
    }

    /*
     * setter는 Jackson이 JSON 값을 DTO에 넣을 때 사용한다.
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * age 값을 읽기 위한 getter다.
     */
    public int getAge() {
        return age;
    }

    /*
     * age 값을 넣기 위한 setter다.
     */
    public void setAge(int age) {
        this.age = age;
    }
}
