package io.github.nimbo1999.rest.dto.assembler;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.github.nimbo1999.domain.entity.PhoneNumber;
import io.github.nimbo1999.rest.dto.PhoneNumberDTO;
import io.github.nimbo1999.utils.StringUtils;

public class PhoneNumberAssembler implements Function<List<PhoneNumberDTO>, List<PhoneNumber>> {

    @Override
    public List<PhoneNumber> apply(List<PhoneNumberDTO> phoneNumberDTOList) {
        return phoneNumberDTOList.stream()
            .map(phoneDTO -> convert(phoneDTO))
            .collect(Collectors.toList());
    }

    private PhoneNumber convert(PhoneNumberDTO phoneNumberDTO) {
        String formattedPhoneNumber = StringUtils.removeNonDigits(phoneNumberDTO.getNumber());

        return PhoneNumber.builder()
            .id(phoneNumberDTO.getId())
            .number(formattedPhoneNumber)
            .type(phoneNumberDTO.getType())
            .build();
    }
    
}
