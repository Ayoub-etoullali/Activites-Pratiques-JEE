package com.etoullali.dtos;

import lombok.Data;

@Data
public class CurrentAccountDTO  extends BankAccountDTO{
    private double OverDraft;

}
