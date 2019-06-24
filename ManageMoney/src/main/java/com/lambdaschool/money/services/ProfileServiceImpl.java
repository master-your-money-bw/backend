package com.lambdaschool.money.services;

import com.lambdaschool.money.exceptions.ResourceNotFoundException;
import com.lambdaschool.money.models.Profile;
import com.lambdaschool.money.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "quoteService")
public class ProfileServiceImpl implements ProfileService
{
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<Profile> findAll()
    {
        List<Profile> list = new ArrayList<>();
        profileRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Profile findQuoteById(long id)
    {
        return profileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (profileRepository.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (profileRepository.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                profileRepository.deleteById(id);
            } else
            {
                throw new ResourceNotFoundException(id + " " + authentication.getName());
            }
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Profile save(Profile profile)
    {
        return profileRepository.save(profile);
    }

    @Override
    public List<Profile> findByUserName(String username)
    {
        List<Profile> list = new ArrayList<>();
        profileRepository.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }
}
