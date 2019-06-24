package com.lambdaschool.money.repository;

import com.lambdaschool.money.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
