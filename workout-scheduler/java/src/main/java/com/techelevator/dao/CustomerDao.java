package com.techelevator.dao;

import com.techelevator.model.profile.*;

import java.util.List;

public interface CustomerDao {

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long userId);

    Customer getProfile(String username);

    boolean addNewCustomer(Customer customer);

    boolean addNewCustomerByUsername(String username, String name);

    boolean updateCustomerById(Long userId, Customer customer);

}
