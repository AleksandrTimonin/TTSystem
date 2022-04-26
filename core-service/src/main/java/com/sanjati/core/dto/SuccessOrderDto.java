package com.sanjati.core.dto;

public class SuccessOrderDto {
    private String date;
    private Long id;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SuccessOrderDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SuccessOrderDto(String date, Long id) {
        this.date = date;
        this.id = id;
    }
}
