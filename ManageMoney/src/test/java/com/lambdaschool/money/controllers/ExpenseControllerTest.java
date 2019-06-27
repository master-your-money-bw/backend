package com.lambdaschool.money.controllers;

import com.lambdaschool.money.services.ExpenseService;
import com.lambdaschool.money.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

public class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseService expenseService;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMyExpenses() {
    }

    @Test
    public void getExpenseById() {
    }

    @Test
    public void addExpense() {
    }

    @Test
    public void updateExpense() {
    }

    @Test
    public void deleteExpenseById() {
    }
}