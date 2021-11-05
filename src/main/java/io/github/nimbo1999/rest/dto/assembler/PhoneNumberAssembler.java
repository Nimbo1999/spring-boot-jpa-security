package io.github.nimbo1999.rest.dto.assembler;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.github.nimbo1999.domain.entity.PhoneNumber;
import io.github.nimbo1999.rest.dto.CreatePhoneNumberDTO;
import io.github.nimbo1999.utils.StringUtils;

public class PhoneNumberAssembler implements Function<List<CreatePhoneNumberDTO>, List<PhoneNumber>> {

    @Override
    public List<PhoneNumber> apply(List<CreatePhoneNumberDTO> createPhoneNumberDTO) {
        return createPhoneNumberDTO.stream()
            .map(phoneDTO -> convert(phoneDTO))
            .collect(Collectors.toList());
    }

    private PhoneNumber convert(CreatePhoneNumberDTO createPhoneNumberDTO) {
        String formattedPhoneNumber = StringUtils.removeNonDigits(createPhoneNumberDTO.getNumber());

        return PhoneNumber.builder()
            .number(formattedPhoneNumber)
            .type(createPhoneNumberDTO.getType())
            .build();
    }
    
}
