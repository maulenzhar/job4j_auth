package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.component.UserStore;
import ru.job4j.domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserStore users;
    private final BCryptPasswordEncoder encoder;
    private final ObjectMapper objectMapper;

    public UserController(UserStore users,
                          BCryptPasswordEncoder encoder, ObjectMapper objectMapper) {
        this.users = users;
        this.encoder = encoder;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        if (person.getPassword().length() < 6) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        person.setPassword(encoder.encode(person.getPassword()));
        users.save(person);
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return users.findAll();
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", e.getMessage());
                put("type", e.getClass());
            }
        }));
        log.error(e.getLocalizedMessage());
    }
}
