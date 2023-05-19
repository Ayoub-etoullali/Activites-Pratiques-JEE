package com.etoullali.entities;

import com.etoullali.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity //créer auto la table (Spring)
/**[S1] Mapping Heritage**/
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 4) // discriminatorType par défaut String
/**[S2] Table Per Class**/
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
/**[S3] Joined**/
//@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BankAccount { /**abstract**/
    @Id
    private String id;
    private Date creatAt;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private String currency;

    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount",fetch = FetchType.LAZY) //le charge fait à la demande
    private List<AccountOperation> operations;

}
