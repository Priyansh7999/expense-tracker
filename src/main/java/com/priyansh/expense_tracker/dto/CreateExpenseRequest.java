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
    @NotBlank(message = "Title os required")
    @Size(max=100)
    private String title;

    @Pattern(regexp = "^[A-Za-z ].*$", message = "Description must be a valid string")
    @NotBlank(message = "Description is required")
    @Size(max=1000)
    private String description;

    @NotNull
    @DecimalMin(value = "0.01", message = "Amount must be positive")
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
