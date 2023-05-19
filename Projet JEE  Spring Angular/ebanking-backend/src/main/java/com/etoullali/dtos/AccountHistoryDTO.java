package com.etoullali.dtos;

import com.etoullali.enums.OperationType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AccountHistoryDTO {
    private String id;
    private double balance;
    private int currentPage;
    private int TotalPage;
    private int sizePage;
    List<AccountOperationDTO> accountOperationDTOS;
}
