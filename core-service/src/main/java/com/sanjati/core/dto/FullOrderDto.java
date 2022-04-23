package com.sanjati.core.dto;

public class FullOrderDto {
    private Long id;
    private String username;
    private String title;
    private String description;
    private String status;

    private String executor;

    private String executorCommit;
    private String createdAt;
    private String assignment;
    private String startProgress;
    private String executed;

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getExecutorCommit() {
        return executorCommit;
    }

    public void setExecutorCommit(String executorCommit) {
        this.executorCommit = executorCommit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public String getStartProgress() {
        return startProgress;
    }

    public void setStartProgress(String startProgress) {
        this.startProgress = startProgress;
    }

    public String getExecuted() {
        return executed;
    }

    public void setExecuted(String executed) {
        this.executed = executed;
    }

    public FullOrderDto(Long id, String username, String title, String description, String status, String executor, String executorCommit, String createdAt, String assignment, String startProgress, String executed) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.description = description;
        this.status = status;
        this.executor = executor;
        this.executorCommit = executorCommit;
        this.createdAt = createdAt;
        this.assignment = assignment;
        this.startProgress = startProgress;
        this.executed = executed;
    }

    public FullOrderDto() {
    }
}
