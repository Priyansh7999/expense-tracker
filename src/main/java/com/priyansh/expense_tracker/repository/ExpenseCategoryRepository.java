package com.priyansh.expense_tracker.repository;

import com.priyansh.expense_tracker.entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory,Long> {

}
