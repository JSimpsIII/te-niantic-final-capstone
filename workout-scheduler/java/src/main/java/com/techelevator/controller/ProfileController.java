package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.model.profile.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {

    private ProfileDao profileDao;

    public ProfileController(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<Customer> getAllProfiles() {
        return profileDao.getAllCustomers();
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
        Long id = profileDTO.getCustomer().getCustomerId();
        Customer customer = profileDTO.getCustomer();
        List<Goal> goals = profileDTO.getGoals();
        List<Metric> metrics = profileDTO.getMetrics();
        Profile profile = new Profile(id, customer, goals, metrics);
        return profileDao.createNewProfile(profile);
    }

    @RequestMapping(path = "/users/{id}/goals", method = RequestMethod.GET)
    public List<Goal> getAllGoalsById(@RequestParam Long id) {
        return profileDao.getAllGoalsById(id);
    }

    @RequestMapping(path = "/users/{id}/metrics", method = RequestMethod.GET)
    public List<Metric> getAllMetricsById(@RequestParam Long id) {
        return profileDao.getAllMetricsById(id);
    }
//
//    @RequestMapping(path = "/users/{id}", method = RequestMethod.PUT)
//    public void updateProfileById(@PathVariable Long id, @RequestBody Profile profile){
//        profileDao.updateProfileById(id, profile);
//    }



}
