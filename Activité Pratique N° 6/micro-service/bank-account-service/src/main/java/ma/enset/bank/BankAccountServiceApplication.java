package ma.enset.bank;

import ma.enset.bank.enums.AccountType;
import ma.enset.bank.entities.BankAccount;
import ma.enset.bank.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository){
        return args -> {

            for (int i = 0; i < 10; i++) {
                BankAccount bankAccount=BankAccount.builder()
                        .id(UUID.randomUUID().toString())
                        .createdAt(new Date())
                        .balance(100000+Math.random()*90000)
                        .currency("MAD")
                        .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
                        .build();
                bankAccountRepository.save(bankAccount);
            }

        };
    }
}
