package ma.enset.bank.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bank.enums.AccountType;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder // c'est un pattern très utilisé
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private Double balance; //Double : valeur par défaut est null # double : valeur par défaut est 0
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;

}
