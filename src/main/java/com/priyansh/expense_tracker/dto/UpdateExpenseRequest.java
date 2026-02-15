package com.priyansh.expense_tracker.dto;

import com.priyansh.expense_tracker.entity.PaymentMethod;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateExpenseRequest {

    @Size(max = 100)
    private String title;

    @Size(max = 1000)
    private String description;

    @DecimalMin(value = "0.01", message = "Amount must be positive")
    private Double amount;

    private Long categoryId;

    private PaymentMethod paymentMethod;

    @PastOrPresent
    private LocalDate transactionDate;
}
