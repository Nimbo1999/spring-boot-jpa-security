package io.github.nimbo1999.rest.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @Pattern(regexp = "[\\p{L}0-9 ]+", flags = Pattern.Flag.DOTALL, message = "Customer name must have only Alphanumeric characters")
    @Size(min = 3, max = 100, message = "Customer name must have between 3 and 100 letters")
    @NotEmpty(message = "Customer name it is required")
    private String name;

    @CPF(message = "Customer cpf it is invalid")
    @NotEmpty(message = "Customer cpf it is required")
    private String cpf;

    @Valid
    @NotNull(message = "Customer Address it is required")
    private AddressDTO address;

    @Valid
    @NotEmpty(message = "At least 1 phone number it is required")
    private List<PhoneNumberDTO> phones;

    @Valid
    @NotEmpty(message = "At least 1 email it is required")
    private List<EmailDTO> emails;
}
