package ma.enset.bank.web;

import ma.enset.bank.dto.BankAccountRequestDTO;
import ma.enset.bank.dto.BankAccountResponseDTO;
import ma.enset.bank.entities.BankAccount;
import ma.enset.bank.mappers.AccountMapper;
import ma.enset.bank.repositories.BankAccountRepository;
import ma.enset.bank.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//[M1 : RestController]
@RestController
@RequestMapping("/api") //il faut écrire d'abord "/api" avant pour aller à cette Web Service
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService; // pour la Version DTO
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id) { //c'est un param récupérer à partir de path
        return bankAccountRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(String.format("Account not found"))
                );
    }

    // ----------------DTO----------------

    // respecter les bons pratiques (les normes)
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO saveAccount(@RequestBody BankAccountRequestDTO requestDTO) { //les données récupérer en format JSON
        return accountService.addAccount(requestDTO);
    }

    // ----------------------------------


    @PutMapping("/bankAccounts/{id}") //Put -> update
    public BankAccount updateAccount(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();

        if (bankAccount.getBalance() != null) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedAt() != null) account.setCreatedAt(new Date());
        if (bankAccount.getType() != null) account.setType(bankAccount.getType());
        if (bankAccount.getCurrency() != null) account.setCurrency(bankAccount.getCurrency());

        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }

}
