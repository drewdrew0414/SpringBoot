package com.drewdrew1.forExample.repository;

import com.drewdrew1.forExample.entity.User;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

/*
 * UserRepository는 데이터 저장소 접근을 담당하는 계층이다.
 *
 * 실무에서는 이 계층에서 DB를 조회하거나 저장하는 경우가 많다.
 * 하지만 이 예제에서는 처음 배우기 쉽게 실제 DB 대신 메모리 Map을 사용한다.
 *
 * 주의:
 * - 메모리 저장소는 서버가 실행 중일 때만 데이터가 유지된다.
 * - 서버를 끄고 다시 켜면 아래 생성자에서 넣은 기본 데이터만 다시 생긴다.
 */
@Repository
public class UserRepository {

    /*
     * users는 간단한 메모리 저장소다.
     *
     * key는 유저 id이고, value는 User 객체다.
     *
     * ConcurrentHashMap을 사용한 이유:
     * 스프링 서버는 여러 요청을 동시에 처리할 수 있으므로,
     * 일반 HashMap보다 동시 접근에 조금 더 안전한 자료구조를 사용했다.
     */
    private final Map<Long, User> users = new ConcurrentHashMap<>();

    /*
     * sequence는 id를 1, 2, 3처럼 하나씩 증가시키기 위한 도구다.
     *
     * 실제 DB에서는 AUTO_INCREMENT 또는 sequence 기능이 이 역할을 한다.
     */
    private final AtomicLong sequence = new AtomicLong(0);

    /*
     * Repository Bean이 생성될 때 기본 예시 데이터를 넣어둔다.
     *
     * 그래서 서버를 처음 실행하고 GET /example/users를 호출하면
     * kim, lee 데이터가 바로 보인다.
     */
    public UserRepository() {
        save("kim", 20);
        save("lee", 25);
    }

    /*
     * 전체 유저 목록을 조회한다.
     *
     * Map은 순서가 보장되지 않을 수 있으므로,
     * id 기준으로 정렬해서 항상 같은 순서로 응답되게 했다.
     */
    public List<User> findAll() {
        return users.values()
                .stream()
                .sorted(Comparator.comparing(User::getId))
                .toList();
    }

    /*
     * id로 유저 1명을 조회한다.
     *
     * Optional<User>를 반환하는 이유:
     * - 해당 id의 유저가 있을 수도 있고 없을 수도 있다.
     * - null을 직접 반환하면 NullPointerException 위험이 커진다.
     * - Optional을 사용하면 "없을 수 있음"이 코드에 드러난다.
     */
    public Optional<User> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(users.get(id));
    }

    /*
     * 새 유저를 저장한다.
     *
     * 실제 DB를 사용하는 Repository라면 INSERT SQL 또는 JPA save()가 실행될 수 있다.
     * 이 예제에서는 새 id를 만들고 Map에 User 객체를 넣는다.
     */
    public User save(String name, int age) {
        Long id = sequence.incrementAndGet();
        User user = new User(id, name, age);

        users.put(id, user);

        return user;
    }
}
