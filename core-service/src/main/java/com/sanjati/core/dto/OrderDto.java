package com.sanjati.core.dto;

public class OrderDto {
    private Long id;
    private String createdAt;
    private String status;
    private String title;
    private String description;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrderDto(Long id, String time, String status, String title, String description) {
        this.id = id;
        this.createdAt = time;
        this.status = status;
        this.title = title;
        this.description = description;
    }

    public OrderDto() {
    }
}
