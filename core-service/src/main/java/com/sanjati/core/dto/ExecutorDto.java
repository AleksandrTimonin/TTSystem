package com.sanjati.core.dto;

import java.util.List;

public class ExecutorDto {
    String name;
    List<String> commits;

    public ExecutorDto(String name, List<String> commits) {
        this.name = name;
        this.commits = commits;
    }

    public ExecutorDto() {
    }

}
