package ru.kata.spring.boot.rest_templte.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.kata.spring.boot.rest_templte.model.User;

import java.util.List;

@RestController
@RequestMapping(" http://91.241.64.178:7081/api/users")
public class UserRestController {
    private static final String GET_ALL_USERS = " http://91.241.64.178:7081/api/users";
    private static final String POST_USER = " http://91.241.64.178:7081/api/users";
    private static final String PUT_USER = " http://91.241.64.178:7081/api/users";
    private static final String DELETE_USER = " http://91.241.64.178:7081/api/users/{id}";
    private static RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public List<User> getAllUser(){
        return getAllUser();
    }
}

