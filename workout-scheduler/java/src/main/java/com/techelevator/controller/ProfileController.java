package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.model.profile.*;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {

    private ProfileDao profileDao;

    public ProfileController(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<Profile> getAllProfiles() {
        return profileDao.getAllProfiles();
    }

    @RequestMapping(path = "/users/{name}", method = RequestMethod.GET)
    public Profile getProfileByName(@RequestParam String name){
        return profileDao.getProfileByName(name);
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public Profile getProfileById(@RequestParam Long id){
        return profileDao.getProfileById(id);
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public boolean createNewProfile(@RequestBody ProfileDTO profileDTO) {
        Customer customer = profileDTO.getCustomer();
        Goal goal = profileDTO.getGoal();
        Metrics metrics = profileDTO.getMetrics();
        Profile profile = new Profile(customer, goal, metrics);
        return profileDao.createNewProfile(profile);
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.PUT)
    public void updateProfileById(@PathVariable Long id, @RequestBody Profile profile){
        profileDao.updateProfileById(id, profile);
    }



}
