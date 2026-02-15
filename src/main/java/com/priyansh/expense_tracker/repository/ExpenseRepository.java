package com.priyansh.expense_tracker.repository;

import com.priyansh.expense_tracker.entity.Expense;
import com.priyansh.expense_tracker.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findByPaymentMethod(PaymentMethod paymentMethod);
    List<Expense> findByTransactionDate(LocalDate transactionDate);
    List<Expense> findByCategory_Name(String categoryName);

}
