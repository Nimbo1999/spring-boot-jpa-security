package io.github.nimbo1999.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersistedCustomerEmailsDTO {
    private Long id;
    private String email;
}
