package com.learning.accounts.service;

import com.learning.accounts.dto.CustomerDto;
import com.learning.accounts.dto.response.CustomerResponseDto;
import jakarta.validation.Valid;

public interface CustomerService {


    CustomerResponseDto createCustomerAccount(@Valid CustomerDto customerDto);
}
