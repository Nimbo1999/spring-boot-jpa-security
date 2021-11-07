package io.github.nimbo1999.rest.dto.assembler;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.github.nimbo1999.domain.enums.MaskType;
import io.github.nimbo1999.domain.enums.PhoneType;
import io.github.nimbo1999.rest.dto.PersistedCustomerResponseDTO;
import io.github.nimbo1999.rest.dto.PersistedCustomerAddressDTO;
import io.github.nimbo1999.rest.dto.PersistedCustomerPhoneNumbersDTO;
import io.github.nimbo1999.rest.dto.PersistedCustomerEmailsDTO;
import io.github.nimbo1999.domain.entity.Customer;
import io.github.nimbo1999.domain.entity.Email;
import io.github.nimbo1999.domain.entity.PhoneNumber;
import io.github.nimbo1999.domain.entity.Address;
import io.github.nimbo1999.utils.StringUtils;

public class PersistedCustomerResponseAssembler implements Function<Customer, PersistedCustomerResponseDTO> {

    @Override
    public PersistedCustomerResponseDTO apply(Customer customer) {
        PersistedCustomerAddressDTO persistedAddress = getAddress(customer.getAddress());
        List<PersistedCustomerPhoneNumbersDTO> persistedPhoneNumbers = getPhoneNumbers(customer.getPhones());
        List<PersistedCustomerEmailsDTO> persistedEmails = getEmails(customer.getEmails());
        return PersistedCustomerResponseDTO.builder()
            .id(customer.getId())
            .name(customer.getName())
            .cpf(StringUtils.getStringMask(customer.getCpf(), MaskType.CPF))
            .address(persistedAddress)
            .phones(persistedPhoneNumbers)
            .emails(persistedEmails)
            .build();
    }

    private PersistedCustomerAddressDTO getAddress(Address address) {
        return PersistedCustomerAddressDTO.builder()
            .id(address.getId())
            .postalCode(StringUtils.getStringMask(address.getPostalCode(), MaskType.POSTALCODE))
            .publicPlace(address.getPublicPlace())
            .neighborhood(address.getNeighborhood())
            .city(address.getCity())
            .uf(address.getFederativeUnit())
            .complement(address.getComplement())
            .build();
    }

    private List<PersistedCustomerPhoneNumbersDTO> getPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        return phoneNumbers.stream()
            .map(phoneNumber -> convertPhoneNumber(phoneNumber))
            .collect(Collectors.toList());
    }

    private PersistedCustomerPhoneNumbersDTO convertPhoneNumber(PhoneNumber phoneNumber) {
        MaskType maskType = phoneNumber.getType() == PhoneType.CELL_PHONE
            ? MaskType.CELL_PHONE
            : MaskType.RESIDENTIAL_PHONE;

        return PersistedCustomerPhoneNumbersDTO.builder()
            .id(phoneNumber.getId())
            .number(StringUtils.getStringMask(phoneNumber.getNumber(), maskType))
            .type(phoneNumber.getType().name())
            .build();
    }

    private List<PersistedCustomerEmailsDTO> getEmails(List<Email> emails) {
        return emails.stream()
            .map(email -> convertEmail(email))
            .collect(Collectors.toList());
    }

    private PersistedCustomerEmailsDTO convertEmail(Email email) {
        return new PersistedCustomerEmailsDTO(email.getId(), email.getEmail());
    }

}
