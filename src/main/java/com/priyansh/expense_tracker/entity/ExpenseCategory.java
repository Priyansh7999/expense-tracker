package com.priyansh.expense_tracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="expense_categories")
public class ExpenseCategory {
    @Id
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
}
