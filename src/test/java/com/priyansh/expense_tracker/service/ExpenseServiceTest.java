package com.priyansh.expense_tracker.service;

import com.priyansh.expense_tracker.dto.CreateExpenseRequest;
import com.priyansh.expense_tracker.entity.Expense;
import com.priyansh.expense_tracker.entity.ExpenseCategory;
import com.priyansh.expense_tracker.entity.PaymentMethod;
import com.priyansh.expense_tracker.repository.ExpenseCategoryRepository;
import com.priyansh.expense_tracker.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private ExpenseCategoryRepository categoryRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    void shouldCreateExpenseSuccessfully() {

        CreateExpenseRequest request = new CreateExpenseRequest();
        request.setTitle("Lunch");
        request.setDescription("Office lunch");
        request.setAmount(200.0);
        request.setCategoryId(1L);
        request.setPaymentMethod(PaymentMethod.UPI);
        request.setTransactionDate(LocalDate.now());

        ExpenseCategory category = new ExpenseCategory();
        category.setId(1L);
        category.setName("FOOD");

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(category));


       // Mock save to return the same object with an ID
        when(expenseRepository.save(any(Expense.class)))
                .thenAnswer(invocation -> {
                    Expense expense = invocation.getArgument(0);
                    expense.setId(10L);
                    return expense;
                });


        Expense result = expenseService.createExpense(request);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Lunch", result.getTitle());
        assertEquals(200.0, result.getAmount());
    }
}