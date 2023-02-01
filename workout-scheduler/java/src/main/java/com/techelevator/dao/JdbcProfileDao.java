package com.techelevator.dao;

import com.techelevator.model.profile.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
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
    public List<Profile> getAllProfiles() {
        List<Profile> profiles = new ArrayList<>();
        String sql = sqlQueryGetAll + ";";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Profile profile = mapRowToProfile(results);
            profiles.add(profile);
        }
        return profiles;
    }

    @Override
    public Profile getProfileById(Long userId) {
        String sql = sqlQueryGetAll + " WHERE c.customer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if(results.next()) {
            return mapRowToProfile(results);
        } else {
            throw new RuntimeException("Customer #" + userId + " not found in system.");
        }
    }

    @Override
    public Profile getProfileByName(String name) {
        String sql = sqlQueryGetAll + " WHERE customer_name = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
        if(results.next()) {
            return mapRowToProfile(results);
        } else {
            throw new RuntimeException(name + " not found in system.");
        }
    }

    @Override
    public boolean createNewProfile(Profile profile) {
        /*  customer table
        customer_id int NOT NULL,
        customer_name varchar(50) NOT NULL,
        customer_email varchar(50) NOT NULL,
        photo_link varchar(400),
        height_inches numeric,
         */
        Long id = profile.getUserId();
        String cName = profile.getCustomer().getName();
        String cEmail = profile.getCustomer().getEmail();
        String cPhoto = profile.getCustomer().getPhoto();
        double cHeight = profile.getCustomer().getHeight();
        String sqlCustomerQuery = "INSERT INTO customer " +
                "(customer_id, customer_name, customer_email, photo_link, height_inches) " +
                "VALUES (?, ?, ?, ?, ?);\n";

        /*	metrics table
        metrics_id SERIAL NOT NULL PRIMARY KEY,
        customer_id int NOT NULL,
        metrics_date date NOT NULL,
        current_reps int,
        current_weight_lbs numeric,
        current_time_min numeric,
        current_days int,
        current_misc varchar(50)
         */
        String mDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        int mReps = profile.getMetrics().getReps();
        double mWeight = profile.getMetrics().getWeight();
        double mTime = profile.getMetrics().getTime();
        int mDays = profile.getMetrics().getDays();
        String mMisc = profile.getMetrics().getMisc();
        String sqlMetricsQuery = "INSERT INTO metrics " +
                "(customer_id, metrics_date, current_reps, current_weight_lbs, current_time_min, current_days, current_misc) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);\n";

        /* 	goal table
        goal_misc varchar(50)
        goal_id SERIAL NOT NULL PRIMARY KEY,
        goal_name varchar(50) NOT NULL,
        goal_reps int,
        goal_weight_lbs int,
        goal_time_min numeric,
        goal_days int,
         */
        String gName = profile.getGoal().getName();
        int gReps = profile.getGoal().getReps();
        int gWeight = profile.getGoal().getWeight();
        double gTime = profile.getGoal().getTime();
        int gDays = profile.getGoal().getDays();
        String gMisc = profile.getGoal().getMisc();
        String sqlGoalQuery = "INSERT INTO goal " +
                "(goal_name, goal_reps, goal_weight_lbs, goal_time_min, goal_days, goal_misc) " +
                "VALUES (?, ?, ?, ?, ?, ?);\n";

        /*	customer_goal table
        customer_id int NOT NULL,
        goal_id int NOT NULL,
        goal_date date,
        is_completed boolean
         */
        int gId = profile.getGoal().getId();
        Date gDate = profile.getGoal().getDate();
        boolean gCompleted = profile.getGoal().isCompleted();
        String sqlCustomerGoalQuery = "INSERT INTO customer_goal " +
                "(customer_id, goal_id, goal_date, is_completed) " +
                "VALUES (?, ?, ?, ?);\n";

        String sql = sqlCustomerGoalQuery + sqlMetricsQuery + sqlGoalQuery + sqlCustomerGoalQuery;
        Integer profileId;
        try {
            profileId = jdbcTemplate.queryForObject(sql, Integer.class,
                    id, cName, cEmail, cPhoto, cHeight,
                    id, mDate, mReps, mWeight, mTime, mDays, mMisc,
                    gName, gReps, gWeight, gTime, gDays, gMisc,
                    id, gId, gDate, gCompleted);
        } catch (Exception e) {
            return false;
        }
        return (profileId != null);
    }

    @Override
    public void updateProfileById(Long userId, Profile profile) {
        //TODO: update profile by id
    }

    private Profile mapRowToProfile(SqlRowSet row) {
        Profile profile = new Profile();
        profile.getCustomer().setCustomerId(row.getLong("customer_id"));
        profile.getCustomer().setName(row.getString("customer_name"));
        profile.getCustomer().setEmail(row.getString("customer_email"));
        profile.getCustomer().setPhoto(row.getString("photo_link"));
        profile.getCustomer().setHeight(row.getDouble("height_inches"));
        profile.getMetrics().setId(row.getInt("metrics_id"));
        profile.getMetrics().setDate(row.getDate("metrics_date"));
        profile.getMetrics().setReps(row.getInt("current_reps"));
        profile.getMetrics().setWeight(row.getDouble("current_weight_lbs"));
        profile.getMetrics().setTime(row.getDouble("current_time_min"));
        profile.getMetrics().setDays(row.getInt("current_days"));
        profile.getMetrics().setMisc(row.getString("current_misc"));
        profile.getGoal().setId(row.getInt("goal_id"));
        profile.getGoal().setDate(row.getDate("goal_date"));
        profile.getGoal().setCompleted(row.getBoolean("is_completed"));
        profile.getGoal().setName(row.getString("goal_name"));
        profile.getGoal().setReps(row.getInt("goal_reps"));
        profile.getGoal().setWeight(row.getInt("goal_weight_lbs"));
        profile.getGoal().setTime(row.getDouble("goal_time_min"));
        profile.getGoal().setDays(row.getInt("goal_days"));
        profile.getGoal().setMisc(row.getString("goal_misc"));

        return profile;
    }
}
