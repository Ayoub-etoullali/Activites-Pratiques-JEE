package ma.enset.bank.service;

import ma.enset.bank.dto.BankAccountRequestDTO;
import ma.enset.bank.dto.BankAccountResponseDTO;

public interface AccountService{
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
}
