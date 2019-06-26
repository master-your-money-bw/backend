package com.lambdaschool.money.controllers;

import com.lambdaschool.money.models.Expense;
import com.lambdaschool.money.models.User;
import com.lambdaschool.money.services.ExpenseService;
import com.lambdaschool.money.services.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Return all current user's expenses", response = Expense.class, responseContainer = "List")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                                                 value = "Results page you want to retrieve (0..N)"),
                               @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                                                 value = "Number of records per page."),
                               @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                                                 value = "Sorting criteria in the format: property(,asc|desc). " +
                                                         "Default sort order is ascending. " +
                                                         "Multiple sort criteria are supported.")})
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> getMyExpenses(Authentication authentication,
                                           @PageableDefault(page = 0, size = 100)Pageable pageable) {
        User u = userService.findUserByName(authentication.getName());
        List<Expense> list = expenseService.findAllByUserid(u.getUserid(), pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Return all expense by ID, checks to make sure user has permission", response = Expense.class)
    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<?> getExpenseById(
            Authentication authentication,
            @PathVariable Long id) {
        User u = userService.findUserByName(authentication.getName());
        Expense e = expenseService.findExpenseById(id);
        if (e.getUser() == u){
            return new ResponseEntity<>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @ApiOperation(value = "Adds new expense to current user")
    @PostMapping(value = "/new", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addExpense (
            @Valid
            @RequestBody Expense newExpense,
            Authentication authentication) {
        User u = userService.findUserByName(authentication.getName());
        User currentUser = userService.findUserById(u.getUserid());
        currentUser.getUserExpenses().add(new Expense(newExpense.getExpensename(), newExpense.getAmount(), newExpense.getCategory(), currentUser));
        userService.update(currentUser, u.getUserid());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Updates expense by ID, returns updated expense", response = Expense.class)
    @PutMapping(value = "/update/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateExpense(@RequestBody Expense expense, @PathVariable long id, Authentication authentication) {
        User u = userService.findUserByName(authentication.getName());
        Expense currentExpense = expenseService.findExpenseById(id);
        if (currentExpense.getUser() == u){
            expenseService.update(expense, id);
            return new ResponseEntity<>(currentExpense, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @ApiOperation(value = "Updates expense by ID, returns updated expense", response = void.class)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteExpenseById(
            @PathVariable
            long id,
            Authentication authentication
    ) {
        User u = userService.findUserByName(authentication.getName());
        Expense e = expenseService.findExpenseById(id);
        if (e.getUser() == u) {
            expenseService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
