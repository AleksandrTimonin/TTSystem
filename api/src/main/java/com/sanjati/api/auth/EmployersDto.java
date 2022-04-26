package com.sanjati.api.auth;

import java.util.List;

public class EmployersDto {
    List<SmallUserDto> list;

    public EmployersDto(List<SmallUserDto> list) {
        this.list = list;
    }
}
