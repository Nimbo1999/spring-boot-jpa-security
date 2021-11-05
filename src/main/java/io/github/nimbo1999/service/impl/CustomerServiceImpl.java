package io.github.nimbo1999.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.github.nimbo1999.domain.entity.Customer;
import io.github.nimbo1999.domain.entity.PhoneNumber;
import io.github.nimbo1999.domain.repository.CustomerRepository;
import io.github.nimbo1999.domain.repository.PhoneNumberRepository;
import io.github.nimbo1999.rest.dto.CreateCustomerDTO;
import io.github.nimbo1999.rest.dto.assembler.CustomerAssembler;
import io.github.nimbo1999.rest.dto.assembler.PhoneNumberAssembler;
import io.github.nimbo1999.service.CustomerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;
    private PhoneNumberRepository phoneNumberRepository;

    @Override
    @Transactional
    public Customer saveCustomer(CreateCustomerDTO createCustomerDTO) {
        List<PhoneNumber> phoneNumbers = new PhoneNumberAssembler()
            .apply(createCustomerDTO.getPhones());

        Customer customer = repository.save(new CustomerAssembler().apply(createCustomerDTO));

        List<PhoneNumber> persistedPhones = persistCustomerPhones(phoneNumbers, customer);

        customer.setPhones(persistedPhones);

        return customer;
    }

    private List<PhoneNumber> persistCustomerPhones(List<PhoneNumber> phoneNumbers, Customer customer) {
        return phoneNumbers.stream()
            .map(phoneNumber -> saveCustomerPhoneNumber(phoneNumber, customer))
            .collect(Collectors.toList());
    }

    private PhoneNumber saveCustomerPhoneNumber(PhoneNumber phoneNumber, Customer customer) {
        phoneNumber.setCustomer(customer);
        return phoneNumberRepository.save(phoneNumber);
    }
    
}
