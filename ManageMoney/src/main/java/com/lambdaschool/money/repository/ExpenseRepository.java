package com.lambdaschool.money.repository;

import com.lambdaschool.money.models.Expense;
import com.lambdaschool.money.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    List<Expense> findAllByUser(User user);

    Expense findByExpenseid(long id);
}
