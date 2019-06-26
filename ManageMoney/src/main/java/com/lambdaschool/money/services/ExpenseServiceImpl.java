package com.lambdaschool.money.services;

import com.lambdaschool.money.exceptions.ResourceNotFoundException;
import com.lambdaschool.money.models.Expense;
import com.lambdaschool.money.models.User;
import com.lambdaschool.money.repository.ExpenseRepository;
import com.lambdaschool.money.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "expenseService")
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Expense> findAllByUserid(long id, Pageable pageable) throws ResourceNotFoundException {
        List<Expense> list = new ArrayList<>();
        User u = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
        expenseRepository.findAllByUser(u, pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Expense findExpenseById(long id) {
        Expense e = expenseRepository.findByExpenseid(id);
        return e;
    }

    @Override
    public void delete(long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense save(Expense expense) {
        Expense newExpense = new Expense();

        newExpense.setUser(expense.getUser());

        System.out.println(newExpense);

        if (expense.getAmount() > 0 ) {
            newExpense.setAmount(expense.getAmount());
        }
        if (expense.getCategory() != null) {
            newExpense.setCategory(expense.getCategory());
        }
        if (expense.getExpensename() != null) {
            newExpense.setExpensename(expense.getExpensename());
        }

        return expenseRepository.save(newExpense);
    }

    @Override
    public Expense update(Expense expense, long id) {
        Expense currentExpense = expenseRepository.findByExpenseid(id);


        if (currentExpense != null) {
            if (expense.getAmount() > 0) {
                currentExpense.setAmount(expense.getAmount());
            }
            if (expense.getCategory() != null) {
                currentExpense.setCategory(expense.getCategory());
            }
            if (expense.getExpensename() != null) {
                currentExpense.setExpensename(expense.getExpensename());
            }

            return expenseRepository.save(currentExpense);
        } else {
            throw new EntityNotFoundException(Long.toString(id) + "Not found");
        }
    }
}
