package io.github.nimbo1999.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.github.nimbo1999.domain.entity.Customer;
import io.github.nimbo1999.domain.entity.PhoneNumber;
import io.github.nimbo1999.domain.entity.Email;
import io.github.nimbo1999.domain.repository.CustomerRepository;
import io.github.nimbo1999.domain.repository.PhoneNumberRepository;
import io.github.nimbo1999.domain.repository.EmailRepository;
import io.github.nimbo1999.rest.dto.CustomerDTO;
import io.github.nimbo1999.rest.dto.PageParamsDTO;
import io.github.nimbo1999.rest.dto.assembler.CustomerAssembler;
import io.github.nimbo1999.rest.dto.assembler.PhoneNumberAssembler;
import io.github.nimbo1999.rest.dto.assembler.EmailAssembler;
import io.github.nimbo1999.service.CustomerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final EmailRepository emailRepository;

    @Override
    @Transactional
    public Customer saveCustomer(CustomerDTO createCustomerDTO) {
        Customer customer = repository.save(new CustomerAssembler().apply(createCustomerDTO));

        List<PhoneNumber> phoneNumbers = new PhoneNumberAssembler()
            .apply(createCustomerDTO.getPhones());
        List<PhoneNumber> persistedPhones = persistCustomerPhones(phoneNumbers, customer);
        customer.setPhones(persistedPhones);

        List<Email> emails = new EmailAssembler().apply(createCustomerDTO.getEmails());
        List<Email> persistedEmails = persistCustomerEmails(emails, customer);
        customer.setEmails(persistedEmails);

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

    private List<Email> persistCustomerEmails(List<Email> emails, Customer customer) {
        return emails.stream()
            .map(email -> saveCustomerEmail(email, customer))
            .collect(Collectors.toList());
    }

    private Email saveCustomerEmail(Email email, Customer customer) {
        email.setCustomer(customer);
        return emailRepository.save(email);
    }

    @Override
    public List<Customer> listCustomers(PageParamsDTO pageParams) {
        Pageable page = PageRequest.of(
            pageParams.getPage(),
            pageParams.getSize(),
            Sort.by(pageParams.getSortBy())
        );

        return repository.findAll(page)
            .toList();
    }
    
}
