package com.sanjati.core.converters;

import com.sanjati.core.dto.OrderDto;
import com.sanjati.core.dto.FullOrderDto;
import com.sanjati.core.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class OrderConverter {
private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    //TODO конвертер
    public OrderDto entityToDto(Order order) {

        return new OrderDto(order.getId(), order.getCreatedAt().format(formatter),order.getStatus(),order.getTitle(),order.getDescription());
    }
    public FullOrderDto entityToFullDto(Order order){
        String assignment="не назначено";
        String startProcess = "не назначено";
        String executed = "не назначено";
        String executor = "не назначен";
        String executorCommit = "не заполнено";

        if(order.getAssignment()!= null) assignment = order.getAssignment().format(formatter);
        if(order.getStartProgress()!= null) startProcess = order.getStartProgress().format(formatter);
        if(order.getCompleted()!= null) executed = order.getCompleted().format(formatter);
        if(order.getExecutor()!= null) executor = order.getExecutor();
        if(order.getExecutorCommit()!= null) executorCommit = order.getExecutorCommit();

        return new FullOrderDto(order.getId(),
                                order.getUsername(),
                                order.getTitle(),
                                order.getDescription(),
                                order.getStatus(),
                                executor,
                                order.getExecutorCommit(),
                                order.getCreatedAt().format(formatter),
                                assignment,
                                startProcess,
                                executed);
    }
}
