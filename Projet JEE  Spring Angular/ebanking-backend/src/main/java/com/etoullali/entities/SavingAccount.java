package com.etoullali.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
/**Mapping Heritage**/
@DiscriminatorValue("SA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingAccount extends BankAccount{
    private double interestRate;
}
