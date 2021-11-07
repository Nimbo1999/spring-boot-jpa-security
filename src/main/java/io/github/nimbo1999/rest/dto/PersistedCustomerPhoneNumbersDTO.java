package io.github.nimbo1999.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersistedCustomerPhoneNumbersDTO {
    private Long id;
    private String number;
    private String type;
}
