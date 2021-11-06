package io.github.nimbo1999.rest.dto.assembler;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.github.nimbo1999.domain.entity.Email;
import io.github.nimbo1999.rest.dto.EmailDTO;

public class EmailAssembler implements Function<List<EmailDTO>, List<Email>> {
 
    @Override
    public List<Email> apply(List<EmailDTO> createEmailDTO) {
        return createEmailDTO.stream()
            .map(emailDTO -> convert(emailDTO))
            .collect(Collectors.toList());
    }

    private Email convert(EmailDTO createEmailDTO) {
        return Email.builder()
            .email(createEmailDTO.getEmail())
            .build();
    }

}
