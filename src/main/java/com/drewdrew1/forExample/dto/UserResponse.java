package com.drewdrew1.forExample.dto;

import com.drewdrew1.forExample.entity.User;

/*
 * UserResponse는 "응답 DTO"다.
 *
 * Entity를 그대로 클라이언트에게 반환하지 않고,
 * 외부에 보여줄 데이터만 골라서 응답하기 위해 만든 클래스다.
 */
public class UserResponse {

    /*
     * 클라이언트에게 보여줄 유저 고유 번호다.
     */
    private Long id;

    /*
     * 클라이언트에게 보여줄 유저 이름이다.
     */
    private String name;

    /*
     * 클라이언트에게 보여줄 유저 나이다.
     */
    private int age;

    /*
     * 응답 DTO도 기본 생성자가 있으면 Jackson이 객체를 다루기 편하다.
     * 이 예제에서는 우리가 직접 생성자를 호출해서 만들지만,
     * 기본 생성자를 둬도 문제되지 않는다.
     */
    public UserResponse() {
    }

    /*
     * Service에서 응답 객체를 만들 때 사용하는 생성자다.
     */
    public UserResponse(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /*
     * Entity를 Response DTO로 바꾸는 정적 팩토리 메서드다.
     *
     * 이런 메서드를 만들면 변환 코드가 여러 곳에 흩어지지 않고,
     * UserResponse 안에서 한 번에 관리된다.
     */
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getAge()
        );
    }

    /*
     * Jackson은 getter를 보고 JSON 필드를 만든다.
     *
     * getId()가 있으면 JSON에 "id" 필드가 생긴다.
     */
    public Long getId() {
        return id;
    }

    /*
     * getName()이 있으면 JSON에 "name" 필드가 생긴다.
     */
    public String getName() {
        return name;
    }

    /*
     * getAge()가 있으면 JSON에 "age" 필드가 생긴다.
     */
    public int getAge() {
        return age;
    }
}
