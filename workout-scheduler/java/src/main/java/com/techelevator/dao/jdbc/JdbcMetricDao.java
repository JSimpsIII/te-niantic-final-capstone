package com.techelevator.dao.jdbc;

import com.techelevator.dao.MetricDao;
import com.techelevator.model.profile.Metric;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JdbcMetricDao implements MetricDao {

    private JdbcTemplate jdbcTemplate;
    public JdbcMetricDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Metric> getAllMetrics(Long userId) {
        List<Metric> metrics = new ArrayList<>();
        String metricsSql = "SELECT * FROM metrics WHERE customer_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(metricsSql, userId);
        while (results.next()) {
            Metric metric = mapRowToMetric(results);
            metrics.add(metric);
        }
        return metrics;
    }

    @Override
    public boolean logNewMetric(Long userId, Metric metric) {
        boolean metricCreated = createNewMetric(userId, metric);
        return metricCreated;
    }

    private boolean createNewMetric(Long userId, Metric metric) {
        int exerciseId = metric.getExerciseId();
        Date date = metric.getDate();
        double reps = metric.getReps();
        double weight = metric.getWeight();
        double time = metric.getTime();
        double sets = metric.getSets();
        int days = metric.getDays();
        String misc = metric.getMisc();

        Integer metricsId;
        String sqlQuery = "INSERT INTO metrics " +
                "(customer_id, exercise_id, metrics_date, current_reps, current_weight_lbs, current_time_min, current_sets, current_days, current_misc) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING metrics_id;";
        try {
            metricsId = jdbcTemplate.queryForObject(sqlQuery, Integer.class,
                    userId, exerciseId, date, reps, weight, time, sets, days, misc);
        } catch (Exception e) {
            return false;
        }
        return (metricsId != null);
    }

    private boolean updateMetric(Long userId, Metric metric) {
        int exerciseId = metric.getExerciseId();
        String mDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        double mReps = metric.getReps();
        double mWeight = metric.getWeight();
        double mTime = metric.getTime();
        double mSets = metric.getSets();
        int mDays = metric.getDays();
        String mMisc = metric.getMisc();

        String sqlQuery = "UPDATE metrics " +
                "SET customer_id = ?, exercise_id = ?, metrics_date = ?, current_reps = ?, current_weight_lbs = ?, current_time_min = ?, current_sets = ?, current_days = ?, current_misc = ? " +
                "WHERE customer_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, userId, exerciseId, mDate, mReps, mWeight, mTime, mSets, mDays, mMisc, userId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private Metric mapRowToMetric(SqlRowSet row) {
        Metric metric = new Metric();
        metric.setId(row.getInt("metrics_id"));
        metric.setCustomerId(row.getLong("customer_id"));
        metric.setExerciseId(row.getInt("exercise_id"));
        metric.setDate(row.getDate("metrics_date"));
        metric.setReps(row.getInt("current_reps"));
        metric.setWeight(row.getDouble("current_weight_lbs"));
        metric.setTime(row.getDouble("current_time_min"));
        metric.setSets(row.getDouble("current_sets"));
        metric.setDays(row.getInt("current_days"));
        metric.setMisc(row.getString("current_misc"));
        return metric;
    }

}
