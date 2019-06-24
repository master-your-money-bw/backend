package com.lambdaschool.money.repository;

import com.lambdaschool.money.models.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long>
{

}
