package com.techelevator.dao;

import com.techelevator.model.profile.Customer;
import com.techelevator.model.profile.Goal;
import com.techelevator.model.profile.Metric;
import com.techelevator.model.profile.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class JdbcProfileDao implements ProfileDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcProfileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Customer customer = mapRowToCustomer(results);
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public Profile getProfileById(Long userId) {
        Customer customer;
        List<Goal> goals = new ArrayList<>();
        List<Metric> metrics = new ArrayList<>();
        String customerSql = "SELECT * FROM customer WHERE c.customer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(customerSql, userId);
        if(results.next()) {
            customer = mapRowToCustomer(results);
        } else {
            throw new RuntimeException("Customer #" + userId + " not found in system.");
        }
        String metricsSql = "SELECT * FROM metrics WHERE customer_id = ?;";
        results = jdbcTemplate.queryForRowSet(metricsSql, userId);
        while (results.next()) {
            Metric metric = mapRowToMetric(results);
            metrics.add(metric);
        }
        String goalSql = "SELECT * FROM goal WHERE customer_id = ?;";
        results = jdbcTemplate.queryForRowSet(goalSql, userId);
        JdbcGoalDao jdbcGoalDao = new JdbcGoalDao(jdbcTemplate);
        while (results.next()) {
            Goal goal = jdbcGoalDao.mapRowToGoal(results);
            goals.add(goal);
        }
        Profile profile = new Profile(userId, customer, goals, metrics);
        return profile;
    }

    @Override
    public Profile getProfileByUsername(String username) {
        String sql = "SELECT * FROM customer WHERE customer_username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        Customer customer;
        List<Goal> goals = new ArrayList<>();
        List<Metric> metrics = new ArrayList<>();
        if(results.next()) {
            customer = mapRowToCustomer(results);
        } else {
            throw new RuntimeException(username + " not found in system.");
        }
        Long userId = customer.getCustomerId();

        String metricsSql = "SELECT * FROM metrics WHERE customer_id = ?;";
        results = jdbcTemplate.queryForRowSet(metricsSql, userId);
        while (results.next()) {
            Metric metric = mapRowToMetric(results);
            metrics.add(metric);
        }
        String goalSql = "SELECT * FROM goal AS g " +
                "JOIN customer_goal AS cg ON g.goal_id = cg.goal_id " +
                "WHERE cg.customer_id = ?;";
        results = jdbcTemplate.queryForRowSet(goalSql, userId);
        JdbcGoalDao jdbcGoalDao = new JdbcGoalDao(jdbcTemplate);
        while (results.next()) {
            Goal goal = jdbcGoalDao.mapRowToGoal(results);
            goals.add(goal);
        }
        Profile profile = new Profile(userId, customer, goals, metrics);
        return profile;
    }

    @Override
    public void createNewProfile(int id) {
        Customer customer = new Customer((long) id);
        addNewCustomer((long) id, customer);
    }

    @Override
    public void createNewProfile(String username) {
        addNewCustomer(username);
    }



    @Override
    public boolean createNewProfile(Profile profile) {
        Long userId = profile.getUserId();
        Customer customer = profile.getCustomer();
        List<Goal> goals = profile.getGoals();
        List<Metric> metrics = profile.getMetrics();
        boolean customerCreated = addNewCustomer(userId, customer);
        JdbcGoalDao jdbcGoalDao = new JdbcGoalDao(jdbcTemplate);
        for (Goal goal : goals) {
            boolean goalAdded = jdbcGoalDao.addNewGoal(userId, goal);
            if (!goalAdded) return false;
        }
        for (Metric metric : metrics) {
            boolean metricAdded = addNewMetric(userId, metric);
            if (!metricAdded) return false;
        }
        return customerCreated;
    }

    @Override
    public List<Metric> getAllMetricsById(Long userId) {
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
    public boolean addNewCustomer(Long userId, Customer customer) {

        boolean hasCustomer = createNewCustomer(userId, customer);
        return hasCustomer;
    }

    @Override
    public boolean addNewCustomer(String username) {
        boolean hasCustomer = createNewCustomer(username);
        return hasCustomer;
    }

    @Override
    public boolean updateCustomerById(Long userId, Customer customer) {

        boolean updatedCustomer = updateCustomer(userId, customer);
        return updatedCustomer;
    }


//    public boolean updateProfileById(Long userId, Profile profile) {
//        boolean updatedCustomer = updateCustomer(userId, profile.getCustomer());
//        List<Goal> goals = profile.getGoals();
//        List<Metric> metrics = profile.getMetrics();
//        for (Goal goal : goals) {
//            boolean goalUpdated = updateGoal(userId, goal);
//            if (!goalUpdated) return false;
//        }
//        for (Metric metric : metrics) {
//            boolean metricUpdated = updateMetric(userId, metric);
//            if (!metricUpdated) return false;
//        }
//        return updatedCustomer;
//    }

    public boolean updateProfileById(Long userId, String email, String photo, double height) {
        Integer customerId;
        String sqlQuery = "UPDATE customer " +
                "SET customer_email = ?, photo_link = ?, height_inches = ? " +
                "WHERE customer_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, email, photo, height, userId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addNewMetric(Long userId, Metric metric) {

        boolean metricCreated = createNewMetric(userId, metric);
        return metricCreated;
    }

    private boolean createNewCustomer(String username) {
         /*  customer table
        customer_id int NOT NULL,
        customer_name varchar(50) NOT NULL,
        customer_email varchar(50),
        photo_link varchar(400),
        height_inches numeric,
         */

        Integer customerId;
        String sqlQuery = "INSERT INTO customer " +
                "(customer_name) " +
                "VALUES (?);\n";
        try {
            customerId = jdbcTemplate.queryForObject(sqlQuery, Integer.class, username);
        } catch (Exception e) {
            return false;
        }
        return (customerId != null);
    }


    private boolean createNewCustomer(Long userId, Customer customer) {
         /*  customer table
        customer_id int NOT NULL,
        customer_name varchar(50) NOT NULL,
        customer_email varchar(50) NOT NULL,
        photo_link varchar(400),
        height_inches numeric,
         */
        String cName = customer.getName();
        String cEmail = customer.getEmail();
        String cPhoto = customer.getPhoto();
        double cHeight = customer.getHeight();

        Integer customerId;
        String sqlQuery = "INSERT INTO customer " +
                "(customer_id, customer_name, customer_email, photo_link, height_inches) " +
                "VALUES (?, ?, ?, ?, ?);\n";
        try {
            customerId = jdbcTemplate.queryForObject(sqlQuery, Integer.class,
                    userId, cName, cEmail, cPhoto, cHeight);
        } catch (Exception e) {
            return false;
        }
        return (customerId != null);
    }

    private boolean createNewMetric(Long userId, Metric metric) {
        /*	metrics table
        metrics_id SERIAL NOT NULL PRIMARY KEY,
        customer_id int NOT NULL,
        metrics_date date NOT NULL,
        current_reps numeric,
        current_weight_lbs numeric,
        current_time_min numeric,
        current_distance_miles numeric;
        current_days int,
        current_misc varchar(50)
         */
        int exerciseId = metric.getExerciseId();
        String mDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        double mReps = metric.getReps();
        double mWeight = metric.getWeight();
        double mTime = metric.getTime();
        double mDistance = metric.getDistance();
        int mDays = metric.getDays();
        String mMisc = metric.getMisc();

        Integer metricsId;
        String sqlQuery = "INSERT INTO metrics " +
                "(customer_id, exercise_id, metrics_date, current_reps, current_weight_lbs, current_time_min, current_distance_miles, current_days, current_misc) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);\n";
        try {
            metricsId = jdbcTemplate.queryForObject(sqlQuery, Integer.class,
                    userId, mDate, mReps, mWeight, mTime, mDays, mMisc);
        } catch (Exception e) {
            return false;
        }
        return (metricsId != null);
    }

    private boolean updateCustomer(Long userId, Customer customer) {
        String cName = customer.getName();
        String cEmail = customer.getEmail();
        String cPhoto = customer.getPhoto();
        double cHeight = customer.getHeight();

        Integer customerId;
        String sqlQuery = "UPDATE customer " +
                "SET customer_name = ?, customer_email = ?, photo_link = ?, height_inches = ? " +
                "WHERE customer_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, cName, cEmail, cPhoto, cHeight, userId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean updateMetric(Long userId, Metric metric) {
        int exerciseId = metric.getExerciseId();
        String mDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        double mReps = metric.getReps();
        double mWeight = metric.getWeight();
        double mTime = metric.getTime();
        double mDistance = metric.getDistance();
        int mDays = metric.getDays();
        String mMisc = metric.getMisc();

        String sqlQuery = "UPDATE metrics " +
                "SET exercise_id = ?, metrics_date = ?, current_reps = ?, current_weight_lbs = ?, current_time_min = ?, current_distance_miles = ?, current_days = ?, current_misc = ? " +
                "WHERE customer_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, exerciseId, mDate, mReps, mWeight, mTime, mDistance, mDays, mMisc, userId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    private Customer mapRowToCustomer(SqlRowSet row) {
        Customer customer = new Customer();
        customer.setCustomerId(row.getLong("customer_id"));
        customer.setName(row.getString("customer_name"));
        customer.setUsername(row.getString("customer_username"));
        customer.setEmail(row.getString("customer_email"));
        customer.setPhoto(row.getString("photo_link"));
        customer.setHeight(row.getDouble("height_inches"));
        return customer;
    }

    private Metric mapRowToMetric(SqlRowSet row) {
        Metric metric = new Metric();
        metric.setId(row.getInt("metrics_id"));
        metric.setDate(row.getDate("metrics_date"));
        metric.setReps(row.getInt("current_reps"));
        metric.setWeight(row.getDouble("current_weight_lbs"));
        metric.setTime(row.getDouble("current_time_min"));
        metric.setDays(row.getInt("current_days"));
        metric.setMisc(row.getString("current_misc"));
        return metric;
    }

}
