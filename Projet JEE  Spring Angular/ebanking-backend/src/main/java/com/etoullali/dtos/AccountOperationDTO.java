package com.etoullali.dtos;

import com.etoullali.enums.OperationType;
import lombok.Data;
import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date date;
    private double amount;
    private String description;
    private OperationType type;
}
