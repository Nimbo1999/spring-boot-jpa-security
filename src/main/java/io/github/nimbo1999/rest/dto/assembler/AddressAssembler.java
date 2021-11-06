package io.github.nimbo1999.rest.dto.assembler;

import java.util.function.Function;

import io.github.nimbo1999.domain.entity.Address;
import io.github.nimbo1999.rest.dto.AddressDTO;
import io.github.nimbo1999.utils.StringUtils;

public class AddressAssembler implements Function<AddressDTO, Address> {

    @Override
    public Address apply(AddressDTO createAddressDTO) {
        String formattedPostalCode = StringUtils.removeNonDigits(createAddressDTO.getPostalCode());

        return Address.builder()
            .postalCode(formattedPostalCode)
            .publicPlace(createAddressDTO.getPublicPlace())
            .neighborhood(createAddressDTO.getNeighborhood())
            .city(createAddressDTO.getCity())
            .federativeUnit(createAddressDTO.getUf())
            .complement(createAddressDTO.getComplement())
            .build();
    }
    
}
