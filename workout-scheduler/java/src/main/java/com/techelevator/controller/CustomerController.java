package com.techelevator.controller;

import com.techelevator.dao.CustomerDao;
import com.techelevator.model.profile.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class CustomerController {

    private CustomerDao customerDao;
    public CustomerController(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @RequestMapping(path = "/users/{username}", method = RequestMethod.GET)
    public Customer getProfile(@PathVariable String username){
        return customerDao.getProfile(username);
    }

//    @RequestMapping(path ="/users/", method = RequestMethod.POST)
//    public boolean addNewCustomerByUsername(@RequestBody String username) {
//        return customerDao.addNewCustomerByUsername(username, newUser.getName());
//    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/users/new", method = RequestMethod.POST)
    public boolean addNewCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setUsername(customerDTO.getUsername());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoto(customerDTO.getPhoto());
        customer.setHeight(customerDTO.getHeight());
        return customerDao.addNewCustomer(customer);
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.PUT)
    public boolean updateCustomerById(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(id);
//        customer.setUsername(customerDTO.getUsername());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoto(customerDTO.getPhoto());
        customer.setHeight(customerDTO.getHeight());
        return customerDao.updateCustomerById(id, customer);
    }

}
