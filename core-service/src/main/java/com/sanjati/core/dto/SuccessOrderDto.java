package com.sanjati.core.dto;

public class SuccessOrderDto {
    private String date;
    private String executor;
    private Long id;

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

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

    public SuccessOrderDto(String date, String username, Long id) {
        this.date = date;
        this.executor = username;
        this.id = id;
    }
}
