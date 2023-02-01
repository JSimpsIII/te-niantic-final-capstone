package com.techelevator.model.dto;

import javax.validation.constraints.NotEmpty;

public class EmployeeDTO {

    @NotEmpty
    private String name;
    @NotEmpty
    private boolean isAdmin;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    private void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
