package com.techelevator.dao;

import com.techelevator.model.profile.Metric;

import java.util.List;

public interface MetricDao {

    List<Metric> getAllMetricsById(Long userId);


    boolean addNewMetric(Long userId, Metric metric);

}
