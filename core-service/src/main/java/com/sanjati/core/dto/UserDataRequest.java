package com.sanjati.core.dto;

public class UserDataRequest {
    public String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public UserDataRequest() {
    }

    public UserDataRequest(String user) {
        this.user = user;
    }
}
