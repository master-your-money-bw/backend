package com.lambdaschool.money.services;

import com.lambdaschool.money.models.Expense;

import java.util.List;

public interface ExpenseService {
    List<Expense> findAllByUserid(long id);

    Expense findExpenseById(long id);

    void delete(long id);

    Expense save(Expense expense);

    Expense update(Expense expense, long id);
}
