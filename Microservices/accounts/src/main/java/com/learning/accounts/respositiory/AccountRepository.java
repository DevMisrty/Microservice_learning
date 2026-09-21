package com.learning.accounts.respositiory;

import com.learning.accounts.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends ListCrudRepository<Account, String> {

    @Query("SELECT COUNT(a) FROM Account a WHERE a.accountNumber LIKE :prefix%")
    long countByAccountNumberStartingWith(String prefix);
}
