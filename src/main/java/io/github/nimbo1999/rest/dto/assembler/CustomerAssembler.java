package io.github.nimbo1999.rest.dto.assembler;

import java.util.function.Function;

import io.github.nimbo1999.domain.entity.Address;
import io.github.nimbo1999.domain.entity.Customer;
import io.github.nimbo1999.rest.dto.CreateCustomerDTO;
import io.github.nimbo1999.utils.StringUtils;

public class CustomerAssembler implements Function<CreateCustomerDTO, Customer> {

    @Override
    public Customer apply(CreateCustomerDTO createCustomerDTO) {
        Address customerAddress = new AddressAssembler().apply(createCustomerDTO.getAddress());

        String formattedCpf = StringUtils.removeNonDigits(createCustomerDTO.getCpf());

        return Customer.builder()
            .name(createCustomerDTO.getName())
            .cpf(formattedCpf)
            .address(customerAddress)
            .build();
    }
    
}
