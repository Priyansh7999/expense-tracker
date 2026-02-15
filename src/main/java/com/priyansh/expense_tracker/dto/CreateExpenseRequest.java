package com.priyansh.expense_tracker.dto;

import com.priyansh.expense_tracker.entity.PaymentMethod;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreateExpenseRequest {

    @Pattern(regexp = "^[A-Za-z ].*$", message = "Title must be a valid string")
    @NotBlank(message = "Title is required")
    @Size(max=100)
    private String title;

    @Pattern(regexp = "^[A-Za-z ].*$", message = "Description must be a valid string")
    @NotBlank(message = "Description is required")
    @Size(max=1000)
    private String description;

    @NotNull
    @DecimalMin(value = "0.01", message = "Amount is required and must be positive")
    @Positive
    private double amount;

    @NotNull(message = "category is required")
    private Long categoryId;

    @NotNull(message = "payment method is required")
    private PaymentMethod paymentMethod;

    @NotNull
    @PastOrPresent(message = "Transaction date should be in the past or in the present")
    private LocalDate transactionDate;
}
