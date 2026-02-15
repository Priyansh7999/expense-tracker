package com.priyansh.expense_tracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="expense_categories")
@Getter
@Setter
public class ExpenseCategory {
    @Id
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
}
