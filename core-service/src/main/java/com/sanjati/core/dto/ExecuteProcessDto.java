package com.sanjati.core.dto;

public class ExecuteProcessDto {
    private String executor;
    private String assignedAt;
    private String acceptedAt;
    private String finishedAt;

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(String assignedAt) {
        this.assignedAt = assignedAt;
    }

    public String getAcceptedAt() {
        return acceptedAt;
    }

    public void setAcceptedAt(String acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    public ExecuteProcessDto() {
    }

    public ExecuteProcessDto(String executor, String assignedAt, String acceptedAt, String finishedAt) {
        this.executor = executor;
        this.assignedAt = assignedAt;
        this.acceptedAt = acceptedAt;
        this.finishedAt = finishedAt;
    }

    @Override
    public String toString() {
        return "ExecuteProcessDto{" +
                "executor='" + executor + '\'' +
                ", assignedAt='" + assignedAt + '\'' +
                ", acceptedAt='" + acceptedAt + '\'' +
                ", postponedAt='" + finishedAt + '\'' +
                '}';
    }
}
