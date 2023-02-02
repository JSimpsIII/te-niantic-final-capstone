package com.techelevator.model.profile;

public class Customer {
    private Long customerId;
    private String username;
    private String name;
    private String email;
    private String photo;
    private Double height;

    public Customer() {
    }

    public Customer(Long customerId, String username, String name, String email) {
        this.customerId = customerId;
        this.username = username;
        this.name = name;
        this.email = email;
    }

    public Customer(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
