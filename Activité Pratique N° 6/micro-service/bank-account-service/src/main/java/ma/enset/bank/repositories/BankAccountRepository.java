package ma.enset.bank.repositories;

import ma.enset.bank.entities.BankAccount;
import ma.enset.bank.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

//[M2 : Spring Data Rest]
@RepositoryRestResource //(maven -> rest) cad demandé à Spring au démarrage, démarre un Web Service RESTfull qui permet de gérer l'entité de type "BankAccount" => auto : créer GET PUT POST... par défaut
public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
    @RestResource(path = "/byType")
    List<BankAccount> findByType(@Param("t") AccountType type);
}
