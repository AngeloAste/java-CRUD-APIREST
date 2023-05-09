package com.java.crudapi.model;

import java.util.Date;

public class UserResponse {

    private Long id;
    private Date created;
    private Date modified;
    private String token;

    public UserResponse(Long id, Date created, Date modified, String token) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
