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
    private String sqlQueryGetAll = "" +
            "SELECT c.customer_id, c.customer_name, c.customer_email, c.photo_link, c.height_inches, \n" +
            "m.metrics_id, m.metrics_date, m.current_reps, m.current_weight_lbs, m.current_time_min, m.current_days, m.current_misc, \n" +
            "cg.goal_id, cg.goal_date, cg.is_completed, \n" +
            "g.goal_name, g.goal_reps, g.goal_weight_lbs, g.goal_time_min, g.goal_days, g.goal_misc FROM customer AS c\n" +
            "JOIN metrics AS m\n" +
            "ON c.customer_id = m.customer_id\n" +
            "JOIN customer_goal as cg\n" +
            "ON cg.customer_id = c.customer_id\n" +
            "JOIN goal AS g\n" +
            "ON g.goal_id = cg.goal_id";

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
        String goalSql = "SELECT * FROM goal AS g " +
                "JOIN customer_goal AS cg ON g.goal_id = cg.goal_id " +
                "WHERE cg.customer_id = ?;";
        results = jdbcTemplate.queryForRowSet(goalSql, userId);
        while (results.next()) {
            Goal goal = mapRowToGoal(results);
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
        while (results.next()) {
            Goal goal = mapRowToGoal(results);
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
        for (Goal goal : goals) {
            boolean goalAdded = addNewGoal(userId, goal);
            if (!goalAdded) return false;
        }
        for (Metric metric : metrics) {
            boolean metricAdded = addNewMetric(userId, metric);
            if (!metricAdded) return false;
        }
        return customerCreated;
    }

    @Override
    public List<Goal> getAllGoalsById(Long userId) {
        List<Goal> goals = new ArrayList<>();
        String goalSql = "SELECT * FROM goal AS g " +
                "JOIN customer_goal AS cg ON g.goal_id = cg.goal_id " +
                "WHERE cg.customer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(goalSql, userId);
        while (results.next()) {
            Goal goal = mapRowToGoal(results);
            goals.add(goal);
        }
        return goals;
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
    public boolean addNewGoal(Long userId, Goal goal) {

        boolean goalCreated = createNewGoal(goal);
        boolean customerGoalCreated = createNewCustomerGoal(userId, goal);
        return goalCreated && customerGoalCreated;
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

    private boolean createNewGoal(Goal goal) {
         /* goal table
        goal_id SERIAL NOT NULL PRIMARY KEY,
        goal_name varchar(50) NOT NULL,
        goal_reps int,
        goal_weight_lbs int,
        goal_time_min numeric,
        goal_days int,
        goal_misc varchar(50)
         */
        String gName = goal.getName();
        int gReps = goal.getReps();
        int gWeight = goal.getWeight();
        double gTime = goal.getTime();
        int gDays = goal.getDays();
        String gMisc = goal.getMisc();

        Integer goalId;
        String sqlQuery = "INSERT INTO goal " +
                "(goal_name, goal_reps, goal_weight_lbs, goal_time_min, goal_days, goal_misc) " +
                "VALUES (?, ?, ?, ?, ?, ?);\n";
        try {
            goalId = jdbcTemplate.queryForObject(sqlQuery, Integer.class,
                    gName, gReps, gWeight, gTime, gDays, gMisc);
        } catch (Exception e) {
            return false;
        }
        return (goalId != null);
    }

    private boolean createNewCustomerGoal(Long userId, Goal goal) {
        /*	customer_goal table
        customer_id int NOT NULL,
        goal_id int NOT NULL,
        goal_date date,
        is_completed boolean
         */
        int gId = goal.getId();
        Date gDate = goal.getDate();
        boolean gCompleted = goal.isCompleted();
        String sqlQuery = "INSERT INTO customer_goal " +
                "(customer_id, goal_id, goal_date, is_completed) " +
                "VALUES (?, ?, ?, ?);\n";

        Integer customerGoalId;
        try {
            customerGoalId = jdbcTemplate.queryForObject(sqlQuery, Integer.class,
                    userId, gId, gDate, gCompleted);
        } catch (Exception e) {
            return false;
        }
        return (customerGoalId != null);
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

    private boolean updateGoal(Long userId, Goal goal) {
        int gId = goal.getId();
        String gName = goal.getName();
        int gReps = goal.getReps();
        int gWeight = goal.getWeight();
        double gTime = goal.getTime();
        int gDays = goal.getDays();
        String gMisc = goal.getMisc();

        String sqlQuery = "UPDATE goal " +
                "SET goal_name = ?, goal_reps = ?, goal_weight_lbs = ?, goal_time_min = ?, goal_days = ?, goal_misc = ? " +
                "WHERE goal_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, gName, gReps, gWeight, gTime, gDays, gMisc, gId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean updateCustomerGoal(Long userId, Goal goal) {
        int gId = goal.getId();
        Date gDate = goal.getDate();
        boolean gCompleted = goal.isCompleted();

        String sqlQuery = "UPDATE customer_goal " +
                "SET goal_date = ?, is_completed = ? " +
                "WHERE customer_id = ? AND goal_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, gDate, gCompleted, userId, gId);
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

    private Goal mapRowToGoal(SqlRowSet row) {
        Goal goal = new Goal();
        goal.setId(row.getInt("goal_id"));
        goal.setDate(row.getDate("goal_date"));
        goal.setCompleted(row.getBoolean("is_completed"));
        goal.setName(row.getString("goal_name"));
        goal.setReps(row.getInt("goal_reps"));
        goal.setWeight(row.getInt("goal_weight_lbs"));
        goal.setTime(row.getDouble("goal_time_min"));
        goal.setDays(row.getInt("goal_days"));
        goal.setMisc(row.getString("goal_misc"));
        return goal;
    }
}
