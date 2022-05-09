package com.sanjati.core.controllers;

import com.sanjati.core.dto.ExecuteProcessDto;
import com.sanjati.core.dto.IncomeUsernameOrderIdDto;
import com.sanjati.core.services.ProcessesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/processes")
@RequiredArgsConstructor
public class ProcessesController {
    private final ProcessesService processesService;
    @PostMapping
    public List<ExecuteProcessDto> getActualProcesses(@RequestHeader String username, @RequestHeader String role, @RequestBody IncomeUsernameOrderIdDto executors) {

        return processesService.findAllActuallyProcessesBySpec(executors.getUsername(),executors.getOrderId());
    }

}
