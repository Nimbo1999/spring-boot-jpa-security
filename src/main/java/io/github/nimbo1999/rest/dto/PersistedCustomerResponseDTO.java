package io.github.nimbo1999.rest.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersistedCustomerResponseDTO {
    private Long id;
    private String name;
    private String cpf;
    private PersistedCustomerAddressDTO address;
    private List<PersistedCustomerPhoneNumbersDTO> phones;
    private List<PersistedCustomerEmailsDTO> emails;
}
