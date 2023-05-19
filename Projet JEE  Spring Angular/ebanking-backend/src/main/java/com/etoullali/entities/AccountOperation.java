package com.etoullali.entities;

import com.etoullali.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double amount;
    private String description;
    @Enumerated(EnumType.STRING)
    private OperationType type;

    @ManyToOne
    BankAccount bankAccount;
}
