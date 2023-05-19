package com.etoullali.web;

import com.etoullali.dtos.*;
import com.etoullali.entities.CurrentAccount;
import com.etoullali.entities.SavingAccount;
import com.etoullali.exceptions.BankAccountNotFoundException;
import com.etoullali.exceptions.CustomerNotFoundException;
import com.etoullali.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Web Service RestFull ou API**/
@RestController
@AllArgsConstructor
@CrossOrigin("*") // autorise tous les domaines
public class BankAccountRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/accounts")
    public List<BankAccountDTO> Accounts(){
        return bankAccountService.bankAccountList();
    }
    @GetMapping("/accounts/{id}")
    public BankAccountDTO getAccount(@PathVariable String id) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(id);
    }
    @GetMapping("/accounts/{id}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String id){
        return bankAccountService.accountHistory(id);
    }
    @GetMapping("/accounts/{id}/pageOperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String id,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(name = "size",defaultValue = "5") int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(id,page,size);
    }
    /*
    @PostMapping("/accounts")
    public BankAccountDTO saveAccount(@RequestBody double initialiseBalance, @RequestBody double overDraft, @RequestBody Long customerId ) {
        CustomerDTO customerDTO=bankAccountService.getCustomerById(customerId);
        BankAccountDTO bankAccountDTO=bankAccountService.getBankAccount(c);
        if (bankAccountDTO instanceof CurrentAccountDTO) {
            return bankAccountService.saveCurrentAccount(initialiseBalance,overDraft,customerId);
        } else {
            return bankAccountService.saveSavingAccount();
        }
    }

/*
    @PutMapping("/accounts/{id}")
    public BankAccountDTO updateAccount(@PathVariable Long id, @RequestBody BankAccountDTO customerDTO){
        customerDTO.setId(id);
        CustomerDTO newCustomerDTO = bankAccountService.updateCustomer(customerDTO);
        return newCustomerDTO;
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable(name = "id") Long id) throws CustomerNotFoundException {
        bankAccountService.deleteCustomer(id);
    }

*/
}
