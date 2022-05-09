package com.sanjati.core.controllers;


import com.sanjati.core.dto.*;
import com.sanjati.api.exceptions.ResourceNotFoundException;
import com.sanjati.core.converters.OrderConverter;
import com.sanjati.core.services.OrderService;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessOrderDto createOrder(@RequestHeader String username, @RequestHeader String role, @RequestBody OrderDetailsDto orderDetailsDto) {
        return orderService.createOrder(username, orderDetailsDto);
    }


    @GetMapping
    public Page<OrderDto> getCurrentUserOrders(@RequestHeader String username, @RequestHeader String role,
                                               @RequestParam(name = "p", defaultValue = "1") Integer page,
                                               @RequestParam(name = "old_date", required = false) String oldDate,
                                               @RequestParam(name = "new_date", required = false) String newDate) {
        if (page < 1) {
            page = 1;
        }
        return orderService.findAllByUsername(oldDate,newDate,page,username).map(
                p->orderConverter.entityToDto(p)
        );
    }
    @GetMapping("/management")
    public Page<FullOrderDto> getAllOrders(@RequestHeader String username, @RequestHeader String role,
                                           @RequestParam(name = "p", defaultValue = "1") Integer page,
                                           @RequestParam(name = "old_date", required = false) String oldDate,
                                           @RequestParam(name = "new_date", required = false) String newDate) {
        if (page < 1) {
            page = 1;
        }
        return orderService.findAllFullOrders(oldDate,newDate,page).map(
                p->orderConverter.entityToFullDto(p)
        );
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id, @RequestHeader String role) {
        return orderConverter.entityToDto(orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("ORDER 404")));

    }
    //employers <GET>

    @GetMapping("/assign")
    public SuccessOrderDto assignMe(@RequestHeader String username, @RequestHeader String role, @RequestParam Long id) {
        return orderService.assignById(id,username,username);
    }
    @PostMapping("/assign")
    public SuccessOrderDto assign(@RequestHeader String username, @RequestHeader String role, @RequestBody AssignUserDto dto) {

        return orderService.assignById(dto.getOrderId(), dto.getUsername(),username);
    }
    @PostMapping("/cancel")
    public SuccessOrderDto cancel(@RequestHeader String username, @RequestHeader String role, @RequestBody Long id) {
        return orderService.cancelById(id, username);
    }

    //assign<POST>
    //assign<GET>
    //cancel<POST>
}
