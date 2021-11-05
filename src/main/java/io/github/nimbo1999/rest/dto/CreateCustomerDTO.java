package io.github.nimbo1999.rest.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerDTO {
    private String name;
    private String cpf;
    private CreateAddressDTO address;
    private List<CreatePhoneNumberDTO> phones;
    private List<CreateEmailDTO> emails;
}
