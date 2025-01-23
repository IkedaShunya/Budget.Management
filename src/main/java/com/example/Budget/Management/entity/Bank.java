package com.example.Budget.Management.entity;

import lombok.Data;

@Data
public class Bank {
    private int bankId;
    private int userId;
    private String bankName;
    private int deleteFlag;

}
