package com.ecom.app.gatewayservice.model;

import java.io.Serializable;
import java.util.List;

public class UserRole implements Serializable {

    private Long id;

    private String name;

    private List<User> users;

    public UserRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
