package com.example.Budget.Management.entity;

import lombok.Data;

@Data
public class Credit {
    private int creditcardId;
    private int userId;
    private String creditcardName;
    private Integer bankId;
    private String bankName;
    private int TransferDate;
    private int kindExpense;
    private int deleteFlag;
}
