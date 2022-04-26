package com.sanjati.api.auth;



public class SmallUserDto {
    String username;
    String date;
    Long id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SmallUserDto(String username, String date, Long id) {
        this.username = username;
        this.date = date;
        this.id = id;
    }

    public SmallUserDto() {
    }
}
