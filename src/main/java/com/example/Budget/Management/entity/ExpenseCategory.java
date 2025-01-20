package com.example.Budget.Management.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * expense_category(支出カテゴリを表すクラス)
 */
@Data
public class ExpenseCategory {
    /** カテゴリID*/
    private int categoryId;
    /** ユーザーID*/
    private int userId;
    /** カテゴリ名*/
    private String categoryName;
    /** 固定費フラグ*/
    private int isFixedCost;
    /** 予測収入金額*/
    private int estimatedAmount;
    /** 開始日時 */
    @DateTimeFormat(pattern = "yyyy-MM")
    private LocalDate startDate;

    public void setStartDate(LocalDate startDate) {
        // 月の初日をデフォルトに設定
        if (startDate != null) {
            this.startDate = startDate.withDayOfMonth(1);
        } else {
            this.startDate = null;
        }
    }
}







