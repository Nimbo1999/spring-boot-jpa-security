package io.github.nimbo1999.rest.controller.advice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.nimbo1999.exceptions.NotFoundException;
import io.github.nimbo1999.rest.ApiFormError;
import io.github.nimbo1999.rest.FieldError;
import io.github.nimbo1999.rest.ApiErrorRespose;

@RestControllerAdvice
public class CustomerControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorRespose handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> formErrors = new ArrayList<>();
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
            formErrors.add(buildFieldError(key, errorsMapper));
        });

        ApiFormError errors = ApiFormError.builder()
            .errors(formErrors)
            .build();

        return ApiErrorRespose.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .content(errors)
            .build();
    }

    private List<String> concatErrorMessages(List<String> oldValue, List<String> newValue) {
        return Stream.of(oldValue, newValue)
            .flatMap(value -> value.stream())
            .collect(Collectors.toList());
    }

    private FieldError buildFieldError(String key, HashMap<String, List<String>> errorMap) {
        return FieldError.builder()
            .field(key)
            .messages(errorMap.get(key))
            .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorRespose handleNotFoundException(NotFoundException ex) {
        ApiFormError errors = ApiFormError.builder()
            .errors(Arrays.asList(
                FieldError.builder()
                .field(ex.getField())
                .messages(Arrays.asList(ex.getMessage()))
                .build())
            )
            .build();

        return ApiErrorRespose.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .content(errors)
            .build();
    }

    @ExceptionHandler(AuthorizationServiceException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorRespose handleUnauthorizedError(AuthorizationServiceException ex) {
        return ApiErrorRespose.builder()
            .status(HttpStatus.UNAUTHORIZED.value())
            .content(ex.getMessage())
            .build();
    }

}
