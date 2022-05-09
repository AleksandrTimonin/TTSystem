package com.sanjati.core.converters;

import com.sanjati.api.utils.AppFormatter;
import com.sanjati.core.dto.OrderDto;
import com.sanjati.core.dto.FullOrderDto;
import com.sanjati.core.entities.Commit;
import com.sanjati.core.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class OrderConverter {


    public OrderDto entityToDto(Order order) {
        List<String> commits;
        if(!order.getCommits().isEmpty()) {
            commits = order.getCommits().stream().map(Commit::getCommit).collect(Collectors.toList());
        } else {
            commits = new ArrayList<>();
            commits.add("не комментировалось");
        }

        return new OrderDto(order.getId(),
                order.getCreatedAt().format(AppFormatter.getFormatter()),
                order.getStatus(),
                order.getTitle(),
                order.getDescription(),
                commits);
    }
    public FullOrderDto entityToFullDto(Order order) {
        //order : id,title,description,username,status,executors, completed,created,updated, list<process>
        List<String> commits;
        if(!order.getCommits().isEmpty()) {
            commits = order.getCommits().stream().map(Commit::getCommit).collect(Collectors.toList());
        } else {
            commits = new ArrayList<>();
            commits.add("не комментировалось");
        }
        FullOrderDto fullData = new FullOrderDto();

        fullData.setId(order.getId());
        fullData.setTitle(order.getTitle());
        fullData.setDescription(order.getDescription());
        fullData.setUsername(order.getUsername());
        fullData.setStatus(order.getStatus());

        if(order.getExecutors()!=null) fullData.setExecutors(order.getExecutors());
        else fullData.setExecutors("не назначен");


        fullData.setCommits(commits);

        fullData.setCreatedAt(order.getCreatedAt().format(AppFormatter.getFormatter()));

        if(order.getCompleted()!=null) fullData.setCompletedAt(order.getCompleted().format(AppFormatter.getFormatter()));
        else  fullData.setCompletedAt("не завернён");



        return fullData;

    }
}
