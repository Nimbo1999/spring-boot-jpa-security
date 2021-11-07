package io.github.nimbo1999.rest.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;

    @NotEmpty(message = "Address postalcode it is required")
    private String postalCode;

    @NotEmpty(message = "Address public place it is required")
    private String publicPlace;

    @NotEmpty(message = "Address neighborhood it is required")
    private String neighborhood;

    @NotEmpty(message = "Address city it is required")
    private String city;

    @NotEmpty(message = "Address uf it is required")
    private String uf;

    private String complement;
}
