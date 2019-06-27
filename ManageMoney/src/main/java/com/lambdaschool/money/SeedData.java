package com.lambdaschool.money;

import com.lambdaschool.money.models.Expense;
import com.lambdaschool.money.models.Role;
import com.lambdaschool.money.models.User;
import com.lambdaschool.money.models.UserRoles;
import com.lambdaschool.money.repository.ExpenseRepository;
import com.lambdaschool.money.repository.RoleRepository;
import com.lambdaschool.money.repository.UserRepository;
import com.lambdaschool.money.services.ExpenseService;
import com.lambdaschool.money.services.RoleService;
import com.lambdaschool.money.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    RoleRepository roleRepository;
    UserRepository userRepository;
    ExpenseRepository expenseRepository;

    public SeedData(RoleRepository roleRepository, UserRepository userRepository, ExpenseRepository expenseRepository)
    {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        roleRepository.save(r1);
        roleRepository.save(r2);

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

        userRepository.save(u1);

        //users
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", users);
        u4.setAge(24);
        u4.setEducation("Associate's");
        u4.setLocation("Austin");

        u1.setIncome(80000);
        u1.setTransportation(1000);
        u1.setFood(300);
        u1.setClothing(100);
        u1.setBills(300);
        u1.setHousing(800);

        u4.getUserExpenses().add(new Expense("exp1", 123, "cat1", u4));
        u4.getUserExpenses().add(new Expense("exp2", 12, "cat2", u4));
        u4.getUserExpenses().add(new Expense("exp3", 1, "cat3", u4));

        userRepository.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);

        u5.setAge(78);
        u5.setEducation("High School");
        u5.setLocation("New York");

        u1.setIncome(155000);
        u1.setTransportation(50);
        u1.setFood(1000);
        u1.setClothing(200);
        u1.setBills(1100);
        u1.setHousing(2000);

        u5.getUserExpenses().add(new Expense("exp1", 123, "cat1", u5));
        u5.getUserExpenses().add(new Expense("exp2", 12, "cat2", u5));
        u5.getUserExpenses().add(new Expense("exp3", 1, "cat3", u5));

        userRepository.save(u5);


    }
}