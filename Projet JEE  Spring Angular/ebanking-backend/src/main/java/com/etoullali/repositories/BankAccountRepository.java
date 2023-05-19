package com.etoullali.repositories;

import com.etoullali.entities.BankAccount;
import com.etoullali.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
