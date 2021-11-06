package io.github.nimbo1999.rest.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String name;
    private String cpf;
    private AddressDTO address;
    private List<PhoneNumberDTO> phones;
    private List<EmailDTO> emails;
}
