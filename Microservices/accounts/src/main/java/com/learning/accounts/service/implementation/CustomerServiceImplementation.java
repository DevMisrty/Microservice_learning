package com.learning.accounts.service.implementation;

import com.learning.accounts.dto.CustomerDto;
import com.learning.accounts.dto.response.CustomerResponseDto;
import com.learning.accounts.model.Customer;
import com.learning.accounts.respositiory.CustomerRepo;
import com.learning.accounts.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplementation implements CustomerService {

    private final CustomerRepo customerRepo;
    private final ModelMapper modelMapper;

    @Override
    public CustomerResponseDto createCustomerAccount(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());

        Customer savedCustomer = customerRepo.save(customer);

        return modelMapper.map(savedCustomer, CustomerResponseDto.class);
    }
}
