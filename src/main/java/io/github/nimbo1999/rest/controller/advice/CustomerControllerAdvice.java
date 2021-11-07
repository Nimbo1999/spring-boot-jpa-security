package io.github.nimbo1999.rest.controller.advice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.nimbo1999.rest.ApiError;
import io.github.nimbo1999.rest.FieldError;

@RestControllerAdvice
public class CustomerControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> errors = new ArrayList<>();
        HashMap<String, List<String>> errorsMapper = new HashMap<>();

        ex.getFieldErrors().forEach(err -> {
            List<String> mapperErrors = errorsMapper.get(err.getField());

            if (mapperErrors != null && mapperErrors.size() > 0) {
                List<String> newErrorMessage = Arrays.asList(err.getDefaultMessage());

                errorsMapper.merge(
                    err.getField(),
                    newErrorMessage,
                    (oldValue, newValue) -> concatErrorMessages(oldValue, newValue));

                return;
            }

            errorsMapper.put(err.getField(), Arrays.asList(err.getDefaultMessage()));
        });

        errorsMapper.keySet().forEach(key -> {
            errors.add(buildFieldError(key, errorsMapper));
        });

        return ApiError.builder()
            .errors(errors)
            .build();
    }

    private List<String> concatErrorMessages(List<String> oldValue, List<String> newValue) {
        return Stream.of(oldValue, newValue)
            .flatMap(value -> value.stream())
            .collect(Collectors.toList());
    }

    private FieldError buildFieldError(String key, HashMap<String, List<String>> errorMap) {
        return FieldError.builder()
            .name(key)
            .messages(errorMap.get(key))
            .build();
    }

}
