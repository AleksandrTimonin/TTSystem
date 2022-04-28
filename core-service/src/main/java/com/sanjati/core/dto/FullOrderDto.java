package com.sanjati.core.dto;

import java.util.List;

public class FullOrderDto {
    private Long id;
    private String username;
    private String title;
    private String description;
    private String status;

    private List<ExecutorDto> executors;
    private List<String> commits;


    private String createdAt;


    private String completedAt;


}
