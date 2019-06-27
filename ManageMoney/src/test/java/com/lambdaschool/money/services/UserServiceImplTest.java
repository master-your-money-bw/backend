package com.lambdaschool.money.services;

import com.lambdaschool.money.MoneyApplicationTests;
import com.lambdaschool.money.exceptions.ResourceNotFoundException;
import com.lambdaschool.money.models.Role;
import com.lambdaschool.money.models.User;
import com.lambdaschool.money.models.UserRoles;
import com.lambdaschool.money.repository.RoleRepository;
import com.lambdaschool.money.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

//SWITCH TO H2 BEFORE RUNNING TESTS

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoneyApplicationTests.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loadUserByUsername() {
    }

    @Test
    public void findUserById() {
        assertEquals("admin", userService.findUserById(3).getUsername());
    }

    @Test
    public void findAll() {
        assertEquals(3, userService.findAll().size());
    }

    @Test
    public void findUserByName() {
        assertEquals(3, userService.findUserByName("admin").getUserid());
    }

    @Test
    public void delete() {
        userService.delete(3);
        assertEquals(2, userService.findAll().size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteNotFound() {
        userService.delete(3000);
        assertEquals(2, userService.findAll().size());
    }

    @Test
    public void save() {
        Role r2 = new Role("asdf");
        roleRepository.save(r2);

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));

        User newUser = new User("john", "asdf", users);

        userRepository.save(newUser);

        assertEquals(4, userService.findAll().size());
    }

    @Test
    public void update() {
//
//        User updatedUser = new User();
//
//        updatedUser.setUsername("admin");
//        updatedUser.setPassword("password");
//        updatedUser.setHousing(121212);
//
//        userService.update(updatedUser, 3);
//
//        assertEquals(121212, userService.findUserById(3).getHousing());
    }
}