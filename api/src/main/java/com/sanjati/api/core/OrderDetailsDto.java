package com.sanjati.api.core;

public class OrderDetailsDto {
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public OrderDetailsDto() {
    }

    public OrderDetailsDto(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
