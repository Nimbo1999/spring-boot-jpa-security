package io.github.nimbo1999.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersistedCustomerAddressDTO {
    private String postalCode;
    private String publicPlace;
    private String neighborhood;
    private String city;
    private String uf;
    private String complement;
}
