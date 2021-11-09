package io.github.nimbo1999.rest;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class ApiErrorRespose {
    private final Integer status;
    private final Object content;
}
