package com.sanjati.api.hw;


import com.sanjati.api.core.SuccessCreatedDto;
import com.sanjati.api.exceptions.AppError;

public class CoreDtoFactory implements AbstractDtoFactory{
    @Override
    public AppErrorReport errorReport() {
        return new AppError("404","всё сломалось");
    }

    @Override
    public Dto successDto() {
        return new SuccessCreatedDto();
    }
}
