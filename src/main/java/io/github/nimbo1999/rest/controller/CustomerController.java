package io.github.nimbo1999.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.nimbo1999.domain.entity.Customer;
import io.github.nimbo1999.rest.dto.CustomerCountDTO;
import io.github.nimbo1999.rest.dto.CustomerDTO;
import io.github.nimbo1999.rest.dto.PageParamsDTO;
import io.github.nimbo1999.rest.dto.PersistedCustomerResponseDTO;
import io.github.nimbo1999.rest.dto.assembler.PersistedCustomerResponseAssembler;
import io.github.nimbo1999.rest.dto.assembler.PersistedCustomerListResponseAssembler;
import io.github.nimbo1999.service.CustomerService;
import io.github.nimbo1999.service.impl.CustomerServiceImpl;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerServiceImpl service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public PersistedCustomerResponseDTO saveCustomer(@RequestBody @Valid CustomerDTO createCustomerDTO) {
        Customer persistedCustomer = service.saveCustomer(createCustomerDTO);
        return new PersistedCustomerResponseAssembler()
            .apply(persistedCustomer);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<PersistedCustomerResponseDTO> getCustomerList(PageParamsDTO pageParams) {
        List<Customer> customerList = service.listCustomers(pageParams);
        return new PersistedCustomerListResponseAssembler()
          .apply(customerList);
    }

    @GetMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public PersistedCustomerResponseDTO getCustomerById(@PathVariable Long id) {
        Customer persistedCustomer = service.getById(id);
        return new PersistedCustomerResponseAssembler()
            .apply(persistedCustomer);
    }

    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public PersistedCustomerResponseDTO updateCustomer(
        @PathVariable Long id,
        @RequestBody @Valid CustomerDTO customerDto
    ) {
        Customer updatedCustomer = service.updateCustomer(id, customerDto);
        return new PersistedCustomerResponseAssembler()
            .apply(updatedCustomer);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCustomerById(@PathVariable Long id) {
        service.deleteCustomerById(id);
    }

    @GetMapping("/count")
    @ResponseStatus(code = HttpStatus.OK)
    public CustomerCountDTO getCustomerCount() {
        return new CustomerCountDTO(service.customerCount());
    }

}
