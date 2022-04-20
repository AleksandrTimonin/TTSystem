package com.sanjati.api.hw;

import com.sanjati.api.core.SuccessCreatedDto;

import com.sanjati.api.exceptions.AppError;
import com.sanjati.api.exceptions.AuthAppError;
import com.sanjati.api.exceptions.ResourceNotFoundException;

public class AuthDtoFactory implements AbstractDtoFactory{
    @Override
    public AppErrorReport errorReport() {
        return new AuthAppError("NOT FOUND","что-то пошло не так");
    }

    @Override
    public Dto successDto() {
        return new SuccessCreatedDto();
    }
}
