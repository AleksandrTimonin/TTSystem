package com.sanjati.api.hw;

public interface AbstractDtoFactory {
    AppErrorReport errorReport();
    Dto       successDto();
}
