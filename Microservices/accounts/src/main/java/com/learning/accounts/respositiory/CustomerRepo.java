package com.learning.accounts.respositiory;

import com.learning.accounts.model.Customer;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends ListCrudRepository<Customer, Long> {

    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
