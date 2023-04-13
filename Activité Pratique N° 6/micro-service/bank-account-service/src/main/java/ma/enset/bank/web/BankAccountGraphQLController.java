package ma.enset.bank.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bank.dto.BankAccountRequestDTO;
import ma.enset.bank.dto.BankAccountResponseDTO;
import ma.enset.bank.entities.BankAccount;
import ma.enset.bank.entities.Customer;
import ma.enset.bank.enums.AccountType;
import ma.enset.bank.repositories.BankAccountRepository;
import ma.enset.bank.repositories.CustomerRepository;
import ma.enset.bank.service.AccountService;
import net.bytebuddy.asm.Advice;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor //for instructeuur : injection de dépendance
public class BankAccountGraphQLController {

    private BankAccountRepository bankAccountRepository;
    private CustomerRepository customerRepository;
    private AccountService accountService;

    @QueryMapping //c'est du Spring Boot GraphQL, qui fait mapping avec "type accountsList" dans le fichier schema
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }
    @QueryMapping
    public BankAccount accountById(@Argument String id){
        return bankAccountRepository.findById(id).orElseThrow(
                ()->new RuntimeException(
                        String.format("Account %s not found",id)
                )
        );
    }
    @MutationMapping //cas de Modification
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }
    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id,@Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(id,bankAccount);
    }
    @MutationMapping
    public void deleteAccount(@Argument String id){
         bankAccountRepository.deleteById(id);
    }
    @QueryMapping
    public  List<Customer> customers(){
        return customerRepository.findAll();
    }
}

//Méthode 1
/*
@Data @NoArgsConstructor @AllArgsConstructor
public class BankAccountDTO {
    private Double balance;
    private String currency;
    private AccountType type;
*/

//Méthode 2
/*
    record BankAccountDTO(Double balance, String type, String currency){ //objet immutable
}
 */

//Méthode 3
/*
utiliser la classe BankAccountRequestDTO
 */
