package io.github.nimbo1999.rest.dto.assembler;

import java.util.function.Function;

import io.github.nimbo1999.domain.entity.Address;
import io.github.nimbo1999.rest.dto.AddressDTO;
import io.github.nimbo1999.utils.StringUtils;

public class AddressAssembler implements Function<AddressDTO, Address> {

    @Override
    public Address apply(AddressDTO addressDTO) {
        String formattedPostalCode = StringUtils.removeNonDigits(addressDTO.getPostalCode());

        return Address.builder()
            .id(addressDTO.getId())
            .postalCode(formattedPostalCode)
            .publicPlace(addressDTO.getPublicPlace())
            .neighborhood(addressDTO.getNeighborhood())
            .city(addressDTO.getCity())
            .federativeUnit(addressDTO.getUf())
            .complement(addressDTO.getComplement())
            .build();
    }
    
}
