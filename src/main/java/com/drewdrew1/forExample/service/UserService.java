package com.drewdrew1.forExample.service;

import com.drewdrew1.forExample.dto.UserCreateRequest;
import com.drewdrew1.forExample.dto.UserResponse;
import com.drewdrew1.forExample.entity.User;
import com.drewdrew1.forExample.repository.UserRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/*
 * UserService는 실제 기능 흐름과 규칙을 담당하는 계층이다.
 *
 * Controller는 HTTP 요청을 받고 응답을 돌려주는 역할에 집중하고,
 * Service는 "회원을 어떻게 조회하고 생성할 것인가" 같은 기능 규칙을 담당한다.
 */
@Service
public class UserService {

    /*
     * Service는 데이터를 직접 저장하지 않고 Repository에게 맡긴다.
     *
     * 이렇게 하면 나중에 저장 방식이 메모리 Map에서 MySQL, JPA, Redis 등으로 바뀌어도
     * Controller 코드는 거의 건드리지 않고 Repository 쪽을 바꾸는 방식으로 확장할 수 있다.
     */
    private final UserRepository userRepository;

    /*
     * 생성자 주입이다.
     *
     * 스프링은 UserService Bean을 만들 때 UserRepository Bean을 찾아서
     * 이 생성자의 파라미터로 자동 주입해준다.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * 기존 간단 예제를 남겨둔 메서드다.
     *
     * 이 메서드는 "Service에 기능을 따로 빼면 Controller가 간단해진다"는 것을 보여준다.
     */
    public String signup() {
        System.out.println("회원가입 진행");

        return "회원가입 완료";
    }

    /*
     * 전체 유저 목록을 조회한다.
     *
     * 흐름:
     * 1. Repository에서 User Entity 목록을 가져온다.
     * 2. Entity를 외부 응답용 DTO인 UserResponse로 변환한다.
     * 3. Controller에게 DTO 목록을 돌려준다.
     */
    public List<UserResponse> findUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::from)
                .toList();
    }

    /*
     * id로 유저 1명을 조회한다.
     *
     * 유저가 없으면 404 NOT_FOUND 에러를 발생시킨다.
     * Controller에서 if문을 반복하지 않고 Service에서 규칙을 처리하는 예시다.
     */
    public UserResponse findUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "사용자를 찾을 수 없습니다. id=" + id
                ));

        return UserResponse.from(user);
    }

    /*
     * 새 유저를 생성한다.
     *
     * 이 메서드는 단순히 저장만 하지 않고,
     * 저장 전에 요청 값이 올바른지도 확인한다.
     *
     * 이런 검증 규칙은 Controller보다 Service에 두는 편이 관리하기 쉽다.
     */
    public UserResponse createUser(UserCreateRequest request) {
        String name = normalizeName(request.getName());
        int age = request.getAge();

        if (name.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "name은 비어 있을 수 없습니다."
            );
        }

        if (age < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "age는 0 이상이어야 합니다."
            );
        }

        User savedUser = userRepository.save(name, age);

        return UserResponse.from(savedUser);
    }

    /*
     * 이름 앞뒤 공백을 제거하는 작은 보조 메서드다.
     *
     * request.getName()이 null일 수도 있으므로,
     * null이면 빈 문자열로 바꿔서 이후 코드에서 NullPointerException이 나지 않게 한다.
     */
    private String normalizeName(String name) {
        if (name == null) {
            return "";
        }

        return name.trim();
    }
}
