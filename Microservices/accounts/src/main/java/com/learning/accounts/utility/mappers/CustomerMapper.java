package com.learning.accounts.utility.mappers;

import com.learning.accounts.dto.response.CustomerResponseDto;
import com.learning.accounts.model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerMapper {

    public CustomerResponseDto toCustomerResponseDto(Customer customer) {
        return CustomerResponseDto.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
