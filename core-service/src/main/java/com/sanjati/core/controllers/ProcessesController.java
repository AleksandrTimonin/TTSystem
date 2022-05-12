package com.sanjati.core.controllers;

import com.sanjati.core.converters.OrderConverter;
import com.sanjati.core.dto.*;
import com.sanjati.core.services.OrderService;
import com.sanjati.core.services.ProcessesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/processes")
@RequiredArgsConstructor
public class ProcessesController {
    private final ProcessesService processesService;
    private final OrderService orderService;

    @PostMapping
    public List<ExecuteProcessDto> getActualProcesses(@RequestHeader String username, @RequestHeader String role, @RequestBody IncomeUsernameOrderIdDto executors) {

        return processesService.findAllActuallyProcessesBySpec(executors.getUsername(),executors.getOrderId());
    }
    @GetMapping("/execution")
    public Page<FullOrderDtoWithProcessInfo> getCurrentUserOrders(@RequestHeader String username, @RequestHeader String role,
                                                                  @RequestParam(name = "p", defaultValue = "1") Integer page,
                                                                  @RequestParam(name = "old_date", required = false) String oldDate,
                                                                  @RequestParam(name = "new_date", required = false) String newDate) {
        if (page < 1) {
            page = 1;
        }
        return orderService.findAllByExecutor(oldDate,newDate,page,username);
    }

}
