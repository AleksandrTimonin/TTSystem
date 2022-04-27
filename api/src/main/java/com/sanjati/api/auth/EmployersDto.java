package com.sanjati.api.auth;

import java.util.List;

public class EmployersDto {
    List<SmallUserDto> list;

    public EmployersDto() {
    }

    public List<SmallUserDto> getList() {
        return list;
    }

    public void setList(List<SmallUserDto> list) {
        this.list = list;
    }

    public EmployersDto(List<SmallUserDto> list) {
        this.list = list;
    }
}
