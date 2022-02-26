package ru.kata.spring.boot.rest_templte;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.kata.spring.boot.rest_templte.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class MainApplication {

    private static final String GET_ALL_USERS = "http://91.241.64.178:7081/api/users";
    private static final String POST_USER = "http://91.241.64.178:7081/api/users";
    private static final String PUT_USER = "http://91.241.64.178:7081/api/users";
    private static final String DELETE_USER = "http://91.241.64.178:7081/api/users/";
    private static List<String> cookies;
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        MainApplication mainApplication = new MainApplication();

        mainApplication.getAllUsers();

        User user3 = new User(3L, "James", "Brown", (byte) 5);
        mainApplication.getNewUser(user3);

        user3.setName("Thomas");
        user3.setLastName("Shelby");
        mainApplication.getUpdateUser(user3);

        mainApplication.getDeleteUser(user3.getId());

    }

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public void getAllUsers() {
        HttpHeaders headers = getHeaders();
        // HttpEntity<String>: To get result as String.
        HttpEntity<String> entity = new HttpEntity<>(headers);
        // Send request with GET method, and Headers.
        ResponseEntity<String> response = restTemplate.exchange(GET_ALL_USERS,
                HttpMethod.GET, entity, String.class);
        cookies = response.getHeaders().get("Set-Cookie");
        for (String c : cookies) {
            System.out.println(c);

        }
        String result = response.getBody();
        System.out.println(result);
    }

    public void getNewUser(User user) {
        HttpHeaders headers = getHeaders();
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        // HttpEntity<String>: To get result as String.
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        // Send request with GET method, and Headers.
        ResponseEntity<String> response = restTemplate.exchange(POST_USER,
                HttpMethod.POST, entity, String.class);

        System.out.println(response.getBody());
    }

    public void getUpdateUser(User user) {
        HttpHeaders headers = getHeaders();
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        // HttpEntity<String>: To get result as String.
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        // Send request with GET method, and Headers.
        ResponseEntity<String> response = restTemplate.exchange(PUT_USER,
                HttpMethod.PUT, entity, String.class);

        System.out.println(response.getBody());
    }

    public void getDeleteUser(long id) {
        HttpHeaders headers = getHeaders();
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));

        HttpEntity<User> entity = new HttpEntity<>(headers);
        // Send request with GET method, and Headers.
        ResponseEntity<String> response = restTemplate.exchange(DELETE_USER + id,
                HttpMethod.DELETE, entity, String.class);

//        System.out.println(restTemplate.exchange(GET_ALL_USERS,
//                HttpMethod.GET, entity, String.class).getBody());
        System.out.println(response.getBody());

    }

}
