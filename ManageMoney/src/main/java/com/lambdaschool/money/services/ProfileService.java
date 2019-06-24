package com.lambdaschool.money.services;

import com.lambdaschool.money.models.Profile;

import java.util.List;

public interface ProfileService
{
    List<Profile> findAll();

    Profile findQuoteById(long id);

    List<Profile> findByUserName(String username);

    void delete(long id);

    Profile save(Profile profile);
}
