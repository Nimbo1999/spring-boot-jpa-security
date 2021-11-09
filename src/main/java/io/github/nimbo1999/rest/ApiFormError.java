package io.github.nimbo1999.rest;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
public class ApiFormError {
    @Getter
    public List<FieldError> errors;
}
