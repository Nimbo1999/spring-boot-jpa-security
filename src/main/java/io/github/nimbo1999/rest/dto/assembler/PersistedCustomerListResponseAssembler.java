package io.github.nimbo1999.rest.dto.assembler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Function;

import io.github.nimbo1999.domain.entity.Customer;
import io.github.nimbo1999.rest.dto.PersistedCustomerResponseDTO;

public class PersistedCustomerListResponseAssembler implements Function<List<Customer>, List<PersistedCustomerResponseDTO>> {

      @Override
      public List<PersistedCustomerResponseDTO> apply(List<Customer> customerList) {
          PersistedCustomerResponseAssembler customerResponseAssembler = new PersistedCustomerResponseAssembler();
          return customerList.stream()
              .map(customer -> customerResponseAssembler.apply(customer))
              .collect(Collectors.toList());
      }
  
}
