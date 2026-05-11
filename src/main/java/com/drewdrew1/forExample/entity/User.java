package com.drewdrew1.forExample.entity;

/*
 * User는 예제에서 사용하는 핵심 데이터 객체다.
 *
 * 실제 DB와 JPA를 사용하는 프로젝트라면 이 클래스에
 * @Entity, @Id, @GeneratedValue 같은 어노테이션이 붙을 수 있다.
 *
 * 이번 예제는 DB 없이 구조를 보여주는 목적이므로
 * 순수 자바 클래스로만 작성했다.
 */
public class User {

    /*
     * 유저를 구분하기 위한 고유 번호다.
     *
     * 실제 DB에서는 보통 Primary Key(PK)에 해당한다.
     */
    private final Long id;

    /*
     * 유저 이름이다.
     */
    private final String name;

    /*
     * 유저 나이다.
     */
    private final int age;

    /*
     * User 객체를 만들 때 반드시 id, name, age를 받도록 했다.
     *
     * 이렇게 하면 값이 비어있는 User가 만들어지는 실수를 줄일 수 있다.
     */
    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /*
     * id 값을 읽기 위한 getter다.
     */
    public Long getId() {
        return id;
    }

    /*
     * name 값을 읽기 위한 getter다.
     */
    public String getName() {
        return name;
    }

    /*
     * age 값을 읽기 위한 getter다.
     */
    public int getAge() {
        return age;
    }
}
