package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.model.profile.*;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class ProfileController {

    private ProfileDao profileDao;

    public ProfileController(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return profileDao.getAllCustomers();
    }

    // changed @RequestParam to @PathVariable
    @RequestMapping(path = "/users/{username}", method = RequestMethod.GET)
    public Profile getProfileByUsername(@PathVariable String username){
        return profileDao.getProfileByUsername(username);
    }

    // commented out because of postman error: ambiguous handler methods - conflicts with path above
//    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
//    public Profile getProfileById(@RequestParam Long id){
//        return profileDao.getProfileById(id);
//    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public boolean createNewProfile(@RequestBody ProfileDTO profileDTO) {
        Long id = profileDTO.getCustomer().getCustomerId();
        Customer customer = profileDTO.getCustomer();
        List<Goal> goals = profileDTO.getGoals();
        List<Metric> metrics = profileDTO.getMetrics();
        Profile profile = new Profile(id, customer, goals, metrics);
        return profileDao.createNewProfile(profile);
    }

//    @RequestMapping(path = "/users/{id}", method = RequestMethod.PUT)
//    public boolean updateProfileById(@RequestParam Long id, @RequestBody ProfileDTO profileDTO) {
//        Customer customer = profileDTO.getCustomer();
//        List<Goal> goals = profileDTO.getGoals();
//        List<Metric> metrics = profileDTO.getMetrics();
//        Profile profile = new Profile(id, customer, goals, metrics);
//        return profileDao.updateProfileById(id, profile);
//    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.PUT)
    public boolean updateProfileById(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return profileDao.updateProfileById(id, customerDTO.getEmail(), customerDTO.getPhoto(), customerDTO.getHeight());
    }

    @RequestMapping(path = "/users/{id}/goals", method = RequestMethod.GET)
    public List<Goal> getAllGoalsById(@PathVariable Long id) {
        return profileDao.getAllGoalsById(id);
    }

    @RequestMapping(path = "/users/{id}/goals", method = RequestMethod.POST)
    public boolean addNewGoal(@RequestParam Long id, @RequestBody GoalDTO goalDTO) {
        String name = goalDTO.getName();
        int reps = goalDTO.getReps();
        int weight = goalDTO.getWeight();
        double time = goalDTO.getTime();
        int days = goalDTO.getDays();
        String misc = goalDTO.getMisc();
        Date date = goalDTO.getDate();
        boolean isCompleted = goalDTO.isCompleted();
        Goal goal = new Goal(name, reps, weight, time, days, misc, date, isCompleted);
        return profileDao.addNewGoal(id, goal);
    }

    @RequestMapping(path = "/users/{id}/metrics", method = RequestMethod.GET)
    public List<Metric> getAllMetricsById(@RequestParam Long id) {
        return profileDao.getAllMetricsById(id);
    }

    @RequestMapping(path = "/users/{id}/metrics", method = RequestMethod.POST)
    public boolean addNewMetric(@RequestParam Long id, @RequestBody MetricDTO metricDTO) {
        Long customerId = metricDTO.getCustomerId();
        int exerciseId = metricDTO.getExerciseId();
        Date date = metricDTO.getDate();
        double reps = metricDTO.getReps();
        double weight = metricDTO.getWeight();
        double time = metricDTO.getTime();
        double distance = metricDTO.getDistance();
        int days = metricDTO.getDays();
        String misc = metricDTO.getMisc();
        Metric metric = new Metric(customerId, exerciseId, date, reps, weight, time, distance, days, misc);
        return profileDao.addNewMetric(id, metric);
    }



}
