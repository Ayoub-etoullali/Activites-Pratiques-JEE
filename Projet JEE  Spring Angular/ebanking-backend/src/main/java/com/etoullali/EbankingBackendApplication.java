package com.etoullali;

import com.etoullali.dtos.BankAccountDTO;
import com.etoullali.dtos.CustomerDTO;
import com.etoullali.entities.*;
import com.etoullali.enums.AccountStatus;
import com.etoullali.enums.OperationType;
import com.etoullali.exceptions.BankAccountNotFoundException;
import com.etoullali.exceptions.CustomerNotFoundException;
import com.etoullali.mappers.BankAccountMapperImpl;
import com.etoullali.repositories.AccountOperationRepository;
import com.etoullali.repositories.BankAccountRepository;
import com.etoullali.repositories.CustomerRepository;
import com.etoullali.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {
        return args -> {

            Stream.of("ayoub", "hayat", "samira", "mustapha", "karima", "radouan").forEach(name -> {
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setName(name);
                customerDTO.setEmail(name + "@gmail.com");
                bankAccountService.saveCustomer(customerDTO);
            });

            bankAccountService.listCustomers().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentAccount(Math.random() * 10000, 100, customer.getId());
                    bankAccountService.saveSavingAccount(Math.random() * 120000, 5, customer.getId());
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });

            List<BankAccountDTO> bankAccountDTOS = bankAccountService.bankAccountList();

            for (BankAccountDTO bankAccountDTO : bankAccountDTOS) {
                for (int i = 0; i < 5; i++) {
                    bankAccountService.debit(bankAccountDTO.getId(), 1000 + Math.random() * 10000, "Debit");
                    bankAccountService.credit(bankAccountDTO.getId(), 1000 + Math.random() * 10000, "Credit");
                }
            }


            System.out.println("\n </> By Ayoub ETOULLALI \n");
        };
    }
}