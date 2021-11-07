package io.github.nimbo1999.rest.dto;

import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    private Long id;

    @Email(message = "invalid customer e-mail")
    private String email;
}
