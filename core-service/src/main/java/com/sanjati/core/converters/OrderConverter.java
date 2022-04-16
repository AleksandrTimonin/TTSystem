package com.sanjati.core.converters;

import com.sanjati.api.core.OrderDto;
import com.sanjati.core.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    //TODO конвертер
    public OrderDto entityToDto(Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return new OrderDto(order.getId(), order.getCreatedAt().format(formatter),order.getStatus(),order.getTitle(),order.getDescription());
    }
}
