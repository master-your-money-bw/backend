package com.lambdaschool.money.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.money.models.Expense;
import com.lambdaschool.money.models.Role;
import com.lambdaschool.money.models.User;
import com.lambdaschool.money.models.UserRoles;
import com.lambdaschool.money.services.ExpenseService;
import com.lambdaschool.money.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseService expenseService;

    @MockBean
    private UserService userService;

    private ArrayList<User> userList;

    @Before
    public void setUp() throws Exception {

        userList = new ArrayList<>();

        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        User u1 = new User("admin", "password", admins);
        u1.setAge(36);
        u1.setEducation("Master's");
        u1.setLocation("Boston");

        u1.setIncome(55000);
        u1.setTransportation(1000);
        u1.setFood(500);
        u1.setClothing(50);
        u1.setBills(600);
        u1.setHousing(1000);

        u1.getUserExpenses().add(new Expense("exp1", 123, "cat1", u1));
        u1.getUserExpenses().add(new Expense("exp2", 12, "cat2", u1));
        u1.getUserExpenses().add(new Expense("exp3", 1, "cat3", u1));
        u1.getUserExpenses().add(new Expense("exp4", 2, "cat3", u1));
        u1.getUserExpenses().add(new Expense("exp5", 3, "cat3", u1));
        u1.getUserExpenses().add(new Expense("exp6", 4, "cat3", u1));
        u1.getUserExpenses().add(new Expense("exp7", 5, "cat3", u1));
        u1.getUserExpenses().add(new Expense("exp8", 6, "cat3", u1));
        u1.getUserExpenses().add(new Expense("exp9", 7, "cat3", u1));
        u1.getUserExpenses().add(new Expense("exp10", 8, "cat3", u1));
        u1.getUserExpenses().add(new Expense("exp11", 9, "cat3", u1));
        u1.getUserExpenses().add(new Expense("exp12", 10, "cat3", u1));

        userList.add(u1);

        //users
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", users);
        u4.setAge(24);
        u4.setEducation("Associate's");
        u4.setLocation("Austin");

        u4.setIncome(80000);
        u4.setTransportation(1000);
        u4.setFood(300);
        u4.setClothing(100);
        u4.setBills(300);
        u4.setHousing(800);

        u4.getUserExpenses().add(new Expense("exp1", 123, "cat1", u4));
        u4.getUserExpenses().add(new Expense("exp2", 12, "cat2", u4));
        u4.getUserExpenses().add(new Expense("exp3", 1, "cat3", u4));

        userList.add(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);

        u5.setAge(78);
        u5.setEducation("High School");
        u5.setLocation("New York");

        u5.setIncome(155000);
        u5.setTransportation(50);
        u5.setFood(1000);
        u5.setClothing(200);
        u5.setBills(1100);
        u5.setHousing(2000);

        u5.getUserExpenses().add(new Expense("exp1", 123, "cat1", u5));
        u5.getUserExpenses().add(new Expense("exp2", 12, "cat2", u5));
        u5.getUserExpenses().add(new Expense("exp3", 1, "cat3", u5));

        userList.add(u5);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMyExpenses() {
//        String apiUrl = "/courses/courses";
//
//        Mockito.when(userService.findAll()).thenReturn(courseList);
//
//        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
//
//        MvcResult r = mockMvc.perform(rb).andReturn();
//        String tr = r.getResponse().getContentAsString();
//
//        ObjectMapper mapper = new ObjectMapper();
//        String er = mapper.writeValueAsString(courseList);
//
//        assertEquals("Rest API Returns List", er, tr);
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