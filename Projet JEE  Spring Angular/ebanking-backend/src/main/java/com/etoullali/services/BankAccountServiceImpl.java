package com.etoullali.services;

import com.etoullali.dtos.*;
import com.etoullali.entities.*;
import com.etoullali.enums.OperationType;
import com.etoullali.exceptions.BalanceNotSufficientException;
import com.etoullali.exceptions.BankAccountNotFoundException;
import com.etoullali.exceptions.CustomerNotFoundException;
import com.etoullali.mappers.BankAccountMapperImpl;
import com.etoullali.repositories.AccountOperationRepository;
import com.etoullali.repositories.BankAccountRepository;
import com.etoullali.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional //fait c'est bien, sinon rollback (annuler les opérations prècedents)
@AllArgsConstructor
/** [M1] slf4j **/ // un framework slf4j pour la gestion de journalisation (log4j)
@Slf4j

public class BankAccountServiceImpl implements BankAccountService {
    private BankAccountRepository bankAccountRepository;
    private CustomerRepository customerRepository;
    private AccountOperationRepository accountOperationRepository;
    private BankAccountMapperImpl mapper;

    /**
     * [M2] slf4j
     **/
    // Logger log= LoggerFactory.getLogger(this.getClass().getName());
    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("Saving new customer");
        Customer customer = mapper.fromCustomerDTO(customerDTO);
        Customer save = customerRepository.save(customer);
        CustomerDTO newCustomerDTO = mapper.fromCustomer(save);
        return newCustomerDTO;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        log.info("Update a customer");
        Customer customer = mapper.fromCustomerDTO(customerDTO);
        Customer save = customerRepository.save(customer);
        CustomerDTO newCustomerDTO = mapper.fromCustomer(save);
        return newCustomerDTO;
    }

    @Override
    public void deleteCustomer(Long id) throws CustomerNotFoundException {
        log.info("Delete a customer");
        if (getCustomerById(id) == null)
            throw new CustomerNotFoundException("Customer not found");
        customerRepository.deleteById(id);
    }

    @Override
    public SavingAccountDTO saveSavingAccount(double initialiseBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        CustomerDTO customerDTO = getCustomerById(customerId);

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatAt(new Date());
        savingAccount.setBalance(initialiseBalance);
        Customer customer = mapper.fromCustomerDTO(customerDTO);
        savingAccount.setCustomer(customer);
        savingAccount.setInterestRate(interestRate);


        return mapper.fromSavingAccount(bankAccountRepository.save(savingAccount));
    }

    public CustomerDTO getCustomerById(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer not found");
        CustomerDTO customerDTO = mapper.fromCustomer(customer);
        return customerDTO;
    }

    @Override
    public CurrentAccountDTO saveCurrentAccount(double initialiseBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        CustomerDTO customerDTO = getCustomerById(customerId);

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatAt(new Date());
        currentAccount.setBalance(initialiseBalance);
        Customer customer = mapper.fromCustomerDTO(customerDTO);
        currentAccount.setCustomer(customer);
        currentAccount.setOverDraft(overDraft);

        return mapper.fromCurrentAccount(bankAccountRepository.save(currentAccount));
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        /**Programmation avancé**/
        List<CustomerDTO> customerDTOS = customerList
                .stream()
                .map(customer -> mapper.fromCustomer(customer))
                .collect(Collectors.toList()); //String to list
        /**Programmation intéractive (classique)**/
        /*
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer c:customerList){
            customerDTOS.add(mapper.fromCustomer(c));
        }
         */
        return customerDTOS;
    }

    @Override
    public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount = getBank(accountId);
        if (bankAccount instanceof CurrentAccount) {
            return mapper.fromCurrentAccount((CurrentAccount) bankAccount);
        } else {
            return mapper.fromSavingAccount((SavingAccount) bankAccount);
        }
    }

    @Override
    public void debit(String id, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = getBank(id);
        if (bankAccount.getBalance() < amount) {
            new BalanceNotSufficientException("Balance not sufficient");
        } else {
            AccountOperation accountOperation = new AccountOperation();
            accountOperation.setBankAccount(bankAccount);
            accountOperation.setType(OperationType.DEBIT);
            accountOperation.setAmount(amount);
            accountOperation.setDate(new Date());
            accountOperation.setDescription(description);
            accountOperationRepository.save(accountOperation);

            double newBalance = bankAccount.getBalance() - amount;
            bankAccount.setBalance(newBalance);
            //prof !! bankAccountRepository.save(bankAccount);
        }
    }

    @Override
    public void credit(String id, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = getBank(id);

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setBankAccount(bankAccount);
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDate(new Date());
        accountOperation.setDescription(description);
        accountOperationRepository.save(accountOperation);

        double newBalance = bankAccount.getBalance() + amount;
        bankAccount.setBalance(newBalance);
        //prof !! bankAccountRepository.save(bankAccount);
    }

    private BankAccount getBank(String id) throws BankAccountNotFoundException {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException {
        debit(accountIdSource, amount, "Transfer to " + accountIdDestination);
        credit(accountIdDestination, amount, "Transfer from " + accountIdSource);
    }

    @Override
    public List<BankAccountDTO> bankAccountList() {
        return bankAccountRepository
                .findAll()
                .stream()
                .map(
                        bankAccount -> {
                            if (bankAccount instanceof CurrentAccount) {
                                return mapper.fromCurrentAccount((CurrentAccount) bankAccount);
                            } else {
                                return mapper.fromSavingAccount((SavingAccount) bankAccount);
                            }
                        }).collect(Collectors.toList());
    }

    @Override
    public List<AccountOperationDTO> accountHistory(String id) {
        List<AccountOperation> accountOperations = accountOperationRepository.findByBankAccount_Id(id);
        return accountOperations
                .stream()
                .map(
                        accountOperation -> mapper.fromAccountOperation(accountOperation)
                )
                .collect(Collectors.toList());
    }

    @Override
    public AccountHistoryDTO getAccountHistory(String id, int page, int size) throws BankAccountNotFoundException {

        BankAccount bankAccount = getBank(id);
        Page<AccountOperation> accountOperations = accountOperationRepository.findByBankAccount_Id(id, PageRequest.of(page, size));

        AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
        List<AccountOperationDTO> accountOperationDTOS = accountOperations
                .getContent()
                .stream()
                .map(
                        accountOperation -> mapper.fromAccountOperation(accountOperation)
                )
                .collect(Collectors.toList());
        accountHistoryDTO.setAccountOperationDTOS(accountOperationDTOS);
        accountHistoryDTO.setId(bankAccount.getId());
        accountHistoryDTO.setBalance(bankAccount.getBalance());
        accountHistoryDTO.setSizePage(size);
        accountHistoryDTO.setTotalPage(accountOperations.getTotalPages());
        accountHistoryDTO.setCurrentPage(page);
        return accountHistoryDTO;
    }

    @Override
    public List<CustomerDTO> searchCustumer(String keyword) {
        List<Customer> customers=customerRepository.findByNameContains(keyword);
        List<CustomerDTO> customerDTOS = customers.stream().map(customer -> mapper.fromCustomer(customer)).collect(Collectors.toList());
        return customerDTOS;
    }
}
