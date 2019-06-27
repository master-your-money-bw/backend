package com.lambdaschool.money.services;

import com.lambdaschool.money.exceptions.ResourceNotFoundException;
import com.lambdaschool.money.models.User;
import com.lambdaschool.money.models.UserRoles;
import com.lambdaschool.money.repository.RoleRepository;
import com.lambdaschool.money.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService
{

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleRepository rolerepos;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userrepos.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }

    public User findUserById(long id) throws ResourceNotFoundException
    {
        return userrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        userrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public User findUserByName(String username) {
        User user = userrepos.findByUsername(username);
        if (user == null) {
            throw new EntityNotFoundException("User could not be found");
        }
        return user;
    }

    @Override
    public void delete(long id)
    {
        if (userrepos.findById(id).isPresent())
        {
            userrepos.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPasswordNoEncrypt(user.getPassword());

        ArrayList<UserRoles> newRoles = new ArrayList<>();
        for (UserRoles ur : user.getUserRoles())
        {
            newRoles.add(new UserRoles(newUser, ur.getRole()));
        }
        newUser.setUserRoles(newRoles);

        if (user.getAge() > 0) {
            newUser.setAge(user.getAge());
        }

        if (user.getLocation() != null && user.getLocation().length() > 0) {
            newUser.setLocation(user.getLocation());
        }

        if (user.getEducation() != null && user.getEducation().length() > 0) {
            newUser.setEducation(user.getEducation());
        }

        if (user.getIncome() > 0) {
            newUser.setIncome(user.getIncome());
        }

        if (user.getTransportation() > 0) {
            newUser.setTransportation(user.getTransportation());
        }

        if (user.getFood() > 0) {
            newUser.setFood(user.getFood());
        }

        if (user.getClothing() > 0) {
            newUser.setClothing(user.getClothing());
        }

        if (user.getBills() > 0) {
            newUser.setBills(user.getBills());
        }

        if (user.getHousing() > 0) {
            newUser.setHousing(user.getHousing());
        }

        return userrepos.save(newUser);
    }


    @Transactional
    @Override
    public User update(User user, long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        if (currentUser != null)
        {
            if (id == currentUser.getUserid())
            {
                if (user.getUsername() != null)
                {
                    currentUser.setUsername(user.getUsername());
                }

                if (user.getPassword() != null)
                {
                    currentUser.setPasswordNoEncrypt(user.getPassword());
                }

                if (user.getUserRoles().size() > 0)
                {
                    // with so many relationships happening, I decided to go
                    // with old school queries
                    // delete the old ones
                    rolerepos.deleteUserRolesByUserId(currentUser.getUserid());

                    // add the new ones
                    for (UserRoles ur : user.getUserRoles())
                    {
                        rolerepos.insertUserRoles(id, ur.getRole().getRoleid());
                    }
                }

                if (user.getAge() > 0) {
                    currentUser.setAge(user.getAge());
                }

                if (user.getLocation() != null && user.getLocation().length() > 0) {
                    currentUser.setLocation(user.getLocation());
                }

                if (user.getEducation() != null && user.getEducation().length() > 0) {
                    currentUser.setEducation(user.getEducation());
                }

                if (user.getIncome() > 0) {
                    currentUser.setIncome(user.getIncome());
                }

                if (user.getTransportation() > 0) {
                    currentUser.setTransportation(user.getTransportation());
                }

                if (user.getFood() > 0) {
                    currentUser.setFood(user.getFood());
                }

                if (user.getClothing() > 0) {
                    currentUser.setClothing(user.getClothing());
                }

                if (user.getBills() > 0) {
                    currentUser.setBills(user.getBills());
                }

                if (user.getHousing() > 0) {
                    currentUser.setHousing(user.getHousing());
                }

                return userrepos.save(currentUser);
            } else
            {
                throw new ResourceNotFoundException(id + " Not current user");
            }
        } else
        {
            throw new ResourceNotFoundException(authentication.getName());
        }

    }
}
