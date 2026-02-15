package com.priyansh.expense_tracker.controller;

import com.priyansh.expense_tracker.dto.CreateExpenseRequest;
import com.priyansh.expense_tracker.dto.UpdateExpenseRequest;
import com.priyansh.expense_tracker.entity.Expense;
import com.priyansh.expense_tracker.entity.PaymentMethod;
import com.priyansh.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    public final ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @PostMapping
    public ResponseEntity<Expense>createExpense(
            @Valid @RequestBody CreateExpenseRequest request){
        Expense expense = expenseService.createExpense(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(expense);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(
            @RequestParam(required = false) PaymentMethod paymentMethod,
            @RequestParam(required = false) LocalDate transactionDate,
            @RequestParam (required = false) String category
    ){
        List<Expense> listOfExpense = expenseService.getAllExpense(paymentMethod,transactionDate,category);
        return ResponseEntity.status(HttpStatus.OK).body(listOfExpense);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id){
        Expense expense = expenseService.getExpenseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(expense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Expense> deleteExpenseById(@PathVariable Long id){
        expenseService.deleteExpenseById(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Expense> updateExpenseById(@PathVariable Long id,@RequestBody UpdateExpenseRequest request){
        Expense expense = expenseService.updateExpense(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(expense);
    }
}
