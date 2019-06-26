package com.lambdaschool.money.repository;

import com.lambdaschool.money.models.Expense;
import com.lambdaschool.money.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ExpenseRepository extends PagingAndSortingRepository<Expense, Long> {
    List<Expense> findAllByUser(User user, Pageable pageable);

    Expense findByExpenseid(long id);
}
