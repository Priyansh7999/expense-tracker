package com.priyansh.expense_tracker.dto;

import com.priyansh.expense_tracker.entity.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateExpenseRequest {
    private String title;
    private String description;
    private double amount;
    private Long categoryId;
    private PaymentMethod paymentMethod;
    private LocalDate transactionDate;
}
