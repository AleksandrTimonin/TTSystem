package com.sanjati.auth.converters;

import com.sanjati.api.auth.UserDto;
import com.sanjati.auth.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public static UserDto modelToDto(User user){
        return new UserDto(user.getFirstName(), user.getLastName(), user.getPatronymic(), user.getEmail(), user.getCompany(), user.getPhone(), user.getOffice(), user.getBuilding());
    }
}
