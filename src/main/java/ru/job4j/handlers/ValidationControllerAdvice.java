package ru.job4j.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors()
                .stream()
                .map(f -> Map.of(
                        f.getField(),
                        String.format("%s. Actual value: %s", f.getDefaultMessage(), f.getRejectedValue())
                ))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }
}
