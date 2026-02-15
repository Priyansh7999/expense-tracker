package com.priyansh.expense_tracker.dto;

import com.priyansh.expense_tracker.entity.PaymentMethod;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateExpenseRequest {

    @NotBlank
    @Size(max=100)
    private String title;

    @NotBlank
    @Size(max=1000)
    private String description;

    @NotNull
    @Positive
    private double amount;

    @NotNull
    private Long categoryId;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    @PastOrPresent
    private LocalDate transactionDate;
}
