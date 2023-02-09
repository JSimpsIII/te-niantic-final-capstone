package com.techelevator.controller;

import com.techelevator.model.profile.Metric;
import com.techelevator.model.profile.MetricDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.techelevator.dao.MetricDao;

import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin
public class MetricsController {

    private MetricDao metricDao;
    public MetricsController(MetricDao metricDao) {
        this.metricDao = metricDao;
    }

    @RequestMapping(path = "/users/{userId}/metrics", method = RequestMethod.GET)
    public List<Metric> getAllMetrics(@PathVariable Long userId) {
        return metricDao.getAllMetrics(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/users/{userId}/log", method = RequestMethod.POST)
    public boolean logNewMetric(@PathVariable Long userId, @RequestBody MetricDTO metricDTO) {

        Long customerId = metricDTO.getCustomerId();
        int exerciseId = metricDTO.getExerciseId();
        Date date = metricDTO.getDate();
        double reps = metricDTO.getReps();
        double weight = metricDTO.getWeight();
        double time = metricDTO.getTime();
        double sets = metricDTO.getSets();
        int days = metricDTO.getDays();
        String misc = metricDTO.getMisc();
        Metric metric = new Metric(customerId, exerciseId, date, reps, weight, time, sets, days, misc);
        return metricDao.logNewMetric(userId, metric);
    }
}
