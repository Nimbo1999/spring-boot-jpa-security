package io.github.nimbo1999.rest.dto.assembler;

import java.util.function.Function;

import io.github.nimbo1999.domain.entity.Address;
import io.github.nimbo1999.domain.entity.Customer;
import io.github.nimbo1999.rest.dto.CustomerDTO;
import io.github.nimbo1999.utils.StringUtils;

public class CustomerAssembler implements Function<CustomerDTO, Customer> {

    @Override
    public Customer apply(CustomerDTO createCustomerDTO) {
        Address customerAddress = new AddressAssembler().apply(createCustomerDTO.getAddress());

        String formattedCpf = StringUtils.removeNonDigits(createCustomerDTO.getCpf());

        return Customer.builder()
            .name(createCustomerDTO.getName())
            .cpf(formattedCpf)
            .address(customerAddress)
            .build();
    }
    
}
