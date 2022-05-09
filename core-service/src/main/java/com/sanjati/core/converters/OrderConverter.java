package com.sanjati.core.converters;

import com.sanjati.api.utils.AppFormatter;
import com.sanjati.core.dto.OrderDto;
import com.sanjati.core.dto.FullOrderDto;
import com.sanjati.core.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class OrderConverter {


    public OrderDto entityToDto(Order order) {

        return new OrderDto(order.getId(),
                order.getCreatedAt().format(AppFormatter.getFormatter()),
                order.getStatus(),
                order.getTitle(),
                order.getDescription(),
                order.getCommit());
    }
    public FullOrderDto entityToFullDto(Order order) {
        //order : id,title,description,username,status,executors, completed,created,updated, list<process>
        FullOrderDto fullData = new FullOrderDto();

        fullData.setId(order.getId());
        fullData.setTitle(order.getTitle());
        fullData.setDescription(order.getDescription());
        fullData.setUsername(order.getUsername());
        fullData.setStatus(order.getStatus());

        if(order.getExecutors()!=null) fullData.setExecutors(order.getExecutors());
        else fullData.setExecutors("не назначен");

        if(order.getCommit()!=null) fullData.setCommits(order.getCommit());
        else  fullData.setCommits("пока не комментировали");

        fullData.setCreatedAt(order.getCreatedAt().format(AppFormatter.getFormatter()));

        if(order.getCompleted()!=null) fullData.setCompletedAt(order.getCompleted().format(AppFormatter.getFormatter()));
        else  fullData.setCompletedAt("не завернён");



        return fullData;

    }
}
