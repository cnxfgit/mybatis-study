package com.cnxf.entity;

import java.io.Serializable;
import java.util.List;

public class RolesUser implements Serializable {
    private Long id;
    private String username;
    private String password;

    private List<Role> roles;
    private UserStore userStore;

    RolesUser(Long id, String storeName){
        this.userStore = new UserStore(id,storeName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public UserStore getUserStore() {
        return userStore;
    }

    public void setUserStore(UserStore userStore) {
        this.userStore = userStore;
    }
}
