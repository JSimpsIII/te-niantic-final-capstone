package com.techelevator.dao.jdbc;

import com.techelevator.dao.CustomerDao;
import com.techelevator.model.profile.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JdbcCustomerDao implements CustomerDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Customer customer = mapRowToCustomer(results);
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(Long userId) {
        Customer customer;
        String sqlQuery = "SELECT * FROM customer WHERE customer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, userId);
        if (results.next()) {
            customer = mapRowToCustomer(results);
        } else {
            throw new RuntimeException("Customer #" + userId + " not found in system.");
        }
        return customer;
    }

    @Override
    public Customer getProfile(String username) {
        String sql = "SELECT * FROM customer WHERE customer_username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        Customer customer;
        if (results.next()) {
            customer = mapRowToCustomer(results);
        } else {
            throw new RuntimeException(username + " not found in system.");
        }
        return customer;
    }

    @Override
    public boolean addNewCustomer(Customer customer) {
        boolean hasCustomer = createNewCustomer(customer);
        return hasCustomer;
    }

    @Override
    public boolean addNewCustomerByUsername(String username, String name) {
        boolean hasCustomer = createNewCustomerByUsername(username, name);
        return hasCustomer;
    }

    @Override
    public boolean updateCustomerById(Long userId, Customer customer) {

        boolean updatedCustomer = updateCustomer(userId, customer);
        return updatedCustomer;
    }



    private boolean createNewCustomerByUsername(String username, String name) {
        Long customerId;
        String sqlQuery = "INSERT INTO customer (customer_username, customer_name) VALUES (?,?) RETURNING customer_id;";
        try {
            customerId = jdbcTemplate.queryForObject(sqlQuery, Long.class, username, name);
        } catch (Exception e) {
            return false;
        }
        return (customerId != null);
    }


    private boolean createNewCustomer(Customer customer) {
        String username = customer.getUsername();
        String name = customer.getName();
        String email = customer.getEmail();
        String photo = customer.getPhoto();
        double height = customer.getHeight();

        Long customerId;
        String sqlQuery = "INSERT INTO customer " +
                "(customer_username, customer_name, customer_email, photo_link, height_inches) " +
                "VALUES (?, ?, ?, ?, ?); ";
        try {
            customerId = jdbcTemplate.queryForObject(sqlQuery, Long.class, username, name, email, photo, height);
        } catch (Exception e) {
            return false;
        }
        return (customerId != null);
    }


    private boolean updateCustomer(Long userId, Customer customer) {
//        String username = customer.getUsername();
        String name = customer.getName();
        String email = customer.getEmail();
        String photo = customer.getPhoto();
        double height = customer.getHeight();

        String sqlQuery = "UPDATE customer " +
                "SET customer_name = ?, customer_email = ?, photo_link = ?, height_inches = ? " +
                "WHERE customer_id = ?; ";
        try {
//            jdbcTemplate.update(sqlQuery, username, name, email, photo, height, userId);
            jdbcTemplate.update(sqlQuery, name, email, photo, height, userId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private Customer mapRowToCustomer(SqlRowSet row) {
        Customer customer = new Customer();
        customer.setCustomerId(row.getLong("customer_id"));
        customer.setUsername(row.getString("customer_username"));
        customer.setName(row.getString("customer_name"));
        customer.setUsername(row.getString("customer_username"));
        customer.setEmail(row.getString("customer_email"));
        customer.setPhoto(row.getString("photo_link"));
        customer.setHeight(row.getDouble("height_inches"));
        return customer;
    }



}
