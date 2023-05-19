package com.etoullali.repositories;

import com.etoullali.dtos.AccountOperationDTO;
import com.etoullali.entities.AccountOperation;
import com.etoullali.entities.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
    List<AccountOperation> findByBankAccount_Id(String accountID); // _Id ou Id : criteria
    Page<AccountOperation> findByBankAccount_Id(String accountID, Pageable pageable); // _Id ou Id : criteria
}
