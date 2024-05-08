package com.uk.person.domain.User;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

// Repository는 rootContext가 가짐 => servletContext보다 먼저임
@Repository
public class UserRepository {

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "ssar", "1234", "010111111111"));
        users.add(new User(2, "cos", "1234", "01022222222"));
        users.add(new User(3, "love", "1234", "01033333333"));

        return users;
    }

    // DB는 RS로 받아서 JavaObject로 만들어줘야하는 것이 Repository의 역할
    public User findById(int id) {
        return new User(1, "ssar", "1234", "010111111111");
    }

    public void save(User user) {
        System.out.println("DB에 insert하기");
    }
}
