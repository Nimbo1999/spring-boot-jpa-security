package io.github.nimbo1999.exceptions;

import lombok.Getter;

public class NotFoundException extends RuntimeException {
    @Getter
    private final String field;

    public NotFoundException(String message) {
        super(message);
        this.field = "NotFound";
    }

    public NotFoundException(String message, String field) {
        super(message);
        this.field = field;
    }
}
