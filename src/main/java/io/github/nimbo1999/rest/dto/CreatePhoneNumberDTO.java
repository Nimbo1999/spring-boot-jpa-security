package io.github.nimbo1999.rest.dto;

import io.github.nimbo1999.domain.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePhoneNumberDTO {
    private String number;
    private PhoneType type;
}
