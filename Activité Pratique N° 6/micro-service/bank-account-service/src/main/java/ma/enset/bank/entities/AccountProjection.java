package ma.enset.bank.entities;

import ma.enset.bank.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = BankAccount.class,name = "p1") //(ex) http://localhost:8082/bankAccounts?projection=p1
public interface AccountProjection {
    public  String getId();
    public AccountType getType();
}
