package com.sanjati.core.controllers;


import com.sanjati.api.core.OrderDetailsDto;
import com.sanjati.api.core.OrderDto;
import com.sanjati.api.core.RolesDto;
import com.sanjati.api.core.SuccessCreatedDto;
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

    @GetMapping("/getRole")
    public RolesDto getRoles(@RequestHeader String username, @RequestHeader String role) {

       RolesDto roles = new RolesDto(role);

        return roles;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessCreatedDto createOrder(@RequestHeader String username, @RequestHeader String role, @RequestBody OrderDetailsDto orderDetailsDto) {
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

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id, @RequestHeader String role) {
        return orderConverter.entityToDto(orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("ORDER 404")));
    }
}
