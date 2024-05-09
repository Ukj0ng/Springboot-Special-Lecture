package com.uk.person.web;

import com.uk.person.domain.User.User;
import com.uk.person.domain.User.UserRepository;
import com.uk.person.domain.dto.CommonDto;
import com.uk.person.domain.dto.JoinReqDto;
import com.uk.person.domain.dto.UpdateReqDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// RestController는 servletContext가 띄움
@RestController
public class UserController {

//    private final UserRepository userRepository;  => final은 컴파일 시점에 값(인스턴스가 있어야 하는데)
//    @RequiredArgsConstructor 어노테이션을 작성 후 final을 적으면 알아서 인식해줌
//    @Autowired: 이건 옛날 방식 => junit으로 테스트하기가 어려워서 잘 안씀
    private UserRepository userRepository;

    // 리플렉션에서 servletContext가 UserController를 new할 때, userRepository의 인스턴스가 없는 걸 보고, UserRepository 타입을
    // IoC 컨테이너에서 뒤져서 DI함
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // http://localhost:8080/user
    @GetMapping("/user")
    public CommonDto<List<User>> findAll() {
        System.out.println("UserController.findAll");
        return new CommonDto<>(HttpStatus.OK.value(), userRepository.findAll());    // MessageConverter가 동작하도록 필터가 짜여있음 => JavaObject -> Json String으로 응답해줌
        // 레거시는 MessageConverter를 설정해줘야 하지만 Spring boot는 자동으로 해줌
    }

    // http://localhost:8080/user/1
    @GetMapping("/user/{id}")
    public CommonDto<User> findById(@PathVariable int id) {    // @PathVariable는 url에 있는 변수값을 받아줌
        System.out.println("UserController.findById: " + id);
        return new CommonDto<>(HttpStatus.OK.value(), userRepository.findById(id));
    }

    // http://localhost:8080/user
    @PostMapping("/user")
    // 스프링은 x-www-form-urlencoded의 요청을 받으면 각 매개변수에 매핑해서 넣어줌
    // x-www-form-urlencoded => request.getParameter()
    // queryString은 x-www-form-urlencoded이 타입
    // text/plain, application/json => @RequestBody 어노테이션을 사용
    // ResponseEntity: 따로 Dto쓰지말고 ResponseEntity를 쓰라고 만들어놓음
    public CommonDto<String> save(@RequestBody JoinReqDto dto) {
        System.out.println("UserController.save");
        System.out.println(dto);
        userRepository.save(dto);
//        System.out.println("username: " + username);
//        System.out.println("password: " + password);
//        System.out.println("phone: " + phone);

        return new CommonDto<>(HttpStatus.OK.value(), "ok");
    }

    // http://localhost:8080/user/1
    @DeleteMapping("/user/{id}")
    public CommonDto delete(@PathVariable int id) {
        System.out.println("UserController.delete");
        userRepository.delete(id);  // 만약 userRepository의 함수에서 에러가 발생하면 exception 발생, 굳이 확인 안해도 됨
        return new CommonDto<>(HttpStatus.OK.value());
    }

    // http://localhost:8080/user/1
    @PutMapping("/user/{id}")
    public CommonDto update(@PathVariable int id, UpdateReqDto dto) {
        System.out.println("UserController.update");
        userRepository.update(id, dto);
        return new CommonDto<>(HttpStatus.OK.value());
    }
}
