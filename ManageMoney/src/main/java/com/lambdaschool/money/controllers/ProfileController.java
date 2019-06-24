package com.lambdaschool.money.controllers;

import com.lambdaschool.money.models.Profile;
import com.lambdaschool.money.services.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/quotes")
public class ProfileController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    ProfileService profileService;

    @GetMapping(value = "/quotes",
                produces = {"application/json"})
    public ResponseEntity<?> listAllProfiles(HttpServletRequest request)
    {
        logger.trace(request.getRequestURI() + " accessed");

        List<Profile> allProfiles = profileService.findAll();
        return new ResponseEntity<>(allProfiles, HttpStatus.OK);
    }


    @GetMapping(value = "/quote/{quoteId}",
                produces = {"application/json"})
    public ResponseEntity<?> getProfile(HttpServletRequest request,
                                      @PathVariable
                                              Long quoteId)
    {
        logger.trace(request.getRequestURI() + " accessed");

        Profile q = profileService.findQuoteById(quoteId);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }


    @GetMapping(value = "/username/{userName}",
                produces = {"application/json"})
    public ResponseEntity<?> findProfileByUserName(HttpServletRequest request,
                                                 @PathVariable
                                                         String userName)
    {
        logger.trace(request.getRequestURI() + " accessed");

        List<Profile> theProfiles = profileService.findByUserName(userName);
        return new ResponseEntity<>(theProfiles, HttpStatus.OK);
    }


    @PostMapping(value = "/quote")
    public ResponseEntity<?> addNewProfile(HttpServletRequest request, @Valid
    @RequestBody
            Profile newProfile) throws URISyntaxException
    {
        logger.trace(request.getRequestURI() + " accessed");

        newProfile = profileService.save(newProfile);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newQuoteURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{quoteid}").buildAndExpand(newProfile.getProfileid()).toUri();
        responseHeaders.setLocation(newQuoteURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @DeleteMapping("/quote/{id}")
    public ResponseEntity<?> deleteProfileById(HttpServletRequest request,
                                             @PathVariable
                                                     long id)
    {
        logger.trace(request.getRequestURI() + " accessed");

        profileService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
