package io.github.nimbo1999.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.github.nimbo1999.domain.enums.PhoneType;
import io.github.nimbo1999.validation.NotInValidPhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumberDTO {
    @NotEmpty(message = "Phone number it is required")
    @NotInValidPhoneNumber
    private String number;

    @NotNull(message = "Phone type it is required")
    private PhoneType type;
}
