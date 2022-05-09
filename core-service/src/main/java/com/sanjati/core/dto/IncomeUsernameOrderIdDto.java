package com.sanjati.core.dto;

public class IncomeUsernameOrderIdDto {
    String username;
    Long orderId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public IncomeUsernameOrderIdDto(String username, Long orderId) {
        this.username = username;
        this.orderId = orderId;
    }

    public IncomeUsernameOrderIdDto() {
    }
}
