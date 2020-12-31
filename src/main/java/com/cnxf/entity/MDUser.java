package com.cnxf.entity;

import java.io.Serializable;

public class MDUser implements Serializable {
    private Long id;
    private String username;
    private String mdPassword;

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

    public String getMdPassword() {
        return mdPassword;
    }

    public void setMdPassword(String mdPassword) {
        this.mdPassword = mdPassword;
    }
}
