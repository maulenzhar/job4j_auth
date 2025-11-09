package ru.job4j.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PersonDTO {
    private int id;

    @NotBlank(message = "Login must not be empty")
    private String login;

    @NotBlank(message = "Password must not be empty")
    private String password;
}
