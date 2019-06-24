package com.lambdaschool.money;

import com.lambdaschool.money.models.Profile;
import com.lambdaschool.money.models.Role;
import com.lambdaschool.money.models.User;
import com.lambdaschool.money.models.UserRoles;
import com.lambdaschool.money.services.ProfileService;
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
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    ProfileService profileService;

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        roleService.save(r1);
        roleService.save(r2);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        User u1 = new User("admin", "password", admins);

        Profile p1 = new Profile("A creative man is motivated by the desire to achieve, not by the desire to beat others", u1);
        profileService.save(p1);
        u1.setProfile(p1);

        userService.save(u1);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "ILuvM4th!", users);

        Profile p2 = new Profile("Live long and prosper", u3);
        profileService.save(p2);
        u3.setProfile(p2);

        userService.save(u3);


        //profile-less users
        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);
        userService.save(u5);
    }
}