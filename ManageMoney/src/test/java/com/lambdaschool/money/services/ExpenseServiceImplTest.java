package com.lambdaschool.money.services;

import com.lambdaschool.money.MoneyApplicationTests;
import com.lambdaschool.money.exceptions.ResourceNotFoundException;
import com.lambdaschool.money.models.Expense;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

//SWITCH TO H2 BEFORE RUNNING TESTS

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoneyApplicationTests.class)
public class ExpenseServiceImplTest {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAllByUserid() {
        assertEquals(12, expenseService.findAllByUserid(3, null).size());
    }

    @Test
    public void findExpenseById() {
        assertEquals("exp1", expenseService.findExpenseById(4).getExpensename());
    }

    @Test
    public void delete() {
        expenseService.delete(4);
        assertEquals(11, expenseService.findAllByUserid(3, null).size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteNotFound() {
        expenseService.delete(12341234);
        assertEquals(11, expenseService.findAllByUserid(3, null).size());
    }

    @Test
    public void save() {
        Expense newExpense = new Expense();
        newExpense.setExpensename("asdf");
        newExpense.setUser(userService.findUserById(3));

        expenseService.save(newExpense);

        assertEquals(13, expenseService.findAllByUserid(3, null).size());
    }

    @Test
    public void update() {
        Expense ue = new Expense();
        ue.setExpensename("asdfasdf");

        expenseService.update(ue, 4);

        assertEquals("asdfasdf", expenseService.findExpenseById(4).getExpensename());
    }
}