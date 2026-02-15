package com.priyansh.expense_tracker.service;

import com.priyansh.expense_tracker.dto.CreateExpenseRequest;
import com.priyansh.expense_tracker.dto.UpdateExpenseRequest;
import com.priyansh.expense_tracker.entity.Expense;
import com.priyansh.expense_tracker.entity.ExpenseCategory;
import com.priyansh.expense_tracker.entity.PaymentMethod;
import com.priyansh.expense_tracker.exception.ResourceNotFoundException;
import com.priyansh.expense_tracker.repository.ExpenseCategoryRepository;
import com.priyansh.expense_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private ExpenseCategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository, ExpenseCategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    public Expense createExpense(CreateExpenseRequest request){
        //get category
        ExpenseCategory category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Category Not Found"));
        //create expense
        Expense expense = new Expense();
        expense.setTitle(request.getTitle());
        expense.setDescription(request.getDescription());
        expense.setAmount(request.getAmount());
        expense.setPaymentMethod(request.getPaymentMethod());
        expense.setTransactionDate(request.getTransactionDate());
        expense.setCategory(category);

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpense(PaymentMethod paymentMethod, LocalDate transactionDate, String category){
        if(paymentMethod!=null){
            return expenseRepository.findByPaymentMethod(paymentMethod);
        }
        if (transactionDate != null) {
            return expenseRepository.findByTransactionDate(transactionDate);
        }
        if (category != null) {
            return expenseRepository.findByCategory_Name(category);
        }
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Expense not found with id: " + id
                        ));
    }

    public void deleteExpenseById(Long id){
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Expense not found with id: " + id
                        ));

        expenseRepository.delete(expense);
    }

    public Expense updateExpense(Long id, UpdateExpenseRequest request){
        Expense expense = getExpenseById(id);
        if (request.getTitle() != null) {
            expense.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            expense.setDescription(request.getDescription());
        }
        if (request.getAmount() != 0) {
            expense.setAmount(request.getAmount());
        }
        if (request.getPaymentMethod() != null) {
            expense.setPaymentMethod(request.getPaymentMethod());
        }
        if (request.getTransactionDate() != null) {
            expense.setTransactionDate(request.getTransactionDate());
        }
        if(request.getCategoryId()!=null){
            ExpenseCategory expenseCategory =
                    categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Category Not Found"));
            expense.setCategory(expenseCategory);
        }
        return expenseRepository.save(expense);
    }
}
