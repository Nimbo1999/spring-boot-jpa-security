package io.github.nimbo1999.rest.dto.assembler;

import java.util.List;
import java.util.function.Function;

import io.github.nimbo1999.domain.entity.Address;
import io.github.nimbo1999.domain.entity.Customer;
import io.github.nimbo1999.domain.entity.Email;
import io.github.nimbo1999.domain.entity.PhoneNumber;
import io.github.nimbo1999.rest.dto.CustomerDTO;
import io.github.nimbo1999.utils.StringUtils;

public class CustomerAssembler implements Function<CustomerDTO, Customer> {

    @Override
    public Customer apply(CustomerDTO customerDto) {
        Address customerAddress = new AddressAssembler().apply(customerDto.getAddress());
        List<PhoneNumber> phoneNumbers = new PhoneNumberAssembler().apply(customerDto.getPhones());
        List<Email> emails = new EmailAssembler().apply(customerDto.getEmails());

        String formattedCpf = StringUtils.removeNonDigits(customerDto.getCpf());

        return Customer.builder()
            .id(customerDto.getId())
            .name(customerDto.getName())
            .cpf(formattedCpf)
            .address(customerAddress)
            .phones(phoneNumbers)
            .emails(emails)
            .build();
    }
    
}
