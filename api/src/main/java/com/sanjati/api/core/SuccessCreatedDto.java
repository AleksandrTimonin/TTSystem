package com.sanjati.api.core;

public class SuccessCreatedDto{
    private String date;
    private Long id;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SuccessCreatedDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SuccessCreatedDto(String date, Long id) {
        this.date = date;
        this.id = id;
    }
}
