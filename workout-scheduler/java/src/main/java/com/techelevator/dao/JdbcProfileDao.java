package com.techelevator.dao;

import com.techelevator.model.profile.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import com.techelevator.model.profile.Customer;

import java.util.ArrayList;
import java.util.List;


@Service
public class JdbcProfileDao implements ProfileDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcProfileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private String getAllSql = "" +
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
        String sql = getAllSql + ";";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Profile profile = mapRowToProfile(results);
            profiles.add(profile);
        }
        return profiles;
    }

    @Override
    public Profile getProfileById(Long userId) {
        String sql = getAllSql + " WHERE c.customer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if(results.next()) {
            return mapRowToProfile(results);
        } else {
            throw new RuntimeException("Customer #" + userId + " not found in system.");
        }
    }

    @Override
    public Profile getProfileByName(String name) {
        String sql = getAllSql + " WHERE customer_name = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
        if(results.next()) {
            return mapRowToProfile(results);
        } else {
            throw new RuntimeException(name + " not found in system.");
        }
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
        profile.getGoal().isCompleted(row.getBoolean("is_completed"));
        profile.getGoal().setName(row.getString("goal_name"));
        profile.getGoal().setReps(row.getInt("goal_reps"));
        profile.getGoal().setWeight(row.getInt("goal_weight_lbs"));
        profile.getGoal().setTime(row.getDouble("goal_time_min"));
        profile.getGoal().setDays(row.getInt("goal_days"));
        profile.getGoal().setMisc(row.getString("goal_misc"));

        return profile;
    }
}
