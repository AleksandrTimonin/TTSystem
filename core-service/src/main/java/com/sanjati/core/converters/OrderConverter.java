package com.sanjati.core.converters;

import com.sanjati.api.utils.AppFormatter;
import com.sanjati.core.dto.FullOrderDtoWithProcessInfo;
import com.sanjati.core.dto.OrderDto;
import com.sanjati.core.dto.FullOrderDto;
import com.sanjati.core.entities.Commit;
import com.sanjati.core.entities.ExecuteProcess;
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
        else  fullData.setCompletedAt("не завершён");



        return fullData;

    }
    public FullOrderDtoWithProcessInfo entityToFullOrderDtoWithProcessInfo(Order order, String executor){
        List<ExecuteProcess> list = order.getProcesses().stream().filter(p-> p.getIsActive()&&p.getExecutor().equals(executor)).collect(Collectors.toList());
        ExecuteProcess exec = list.get(0);
        // один активный и не взятый процесс всегда дролжен быть для исполнителя
        // при завершении перевыставляется, заказ может быть закрыть только начальством


        FullOrderDtoWithProcessInfo fullData =new FullOrderDtoWithProcessInfo();
        List<String> commits;
        if(!order.getCommits().isEmpty()) {
            commits = order.getCommits().stream().map(Commit::getCommit).collect(Collectors.toList());
        } else {
            commits = new ArrayList<>();
            commits.add("не комментировалось");
        }


        fullData.setId(order.getId());
        fullData.setTitle(order.getTitle());
        fullData.setDescription(order.getDescription());
        fullData.setUsername(order.getUsername());
        fullData.setStatus(order.getStatus());
        fullData.setOnConfirm(exec.getOnConfirm());

        if(order.getExecutors()!=null) fullData.setExecutors(order.getExecutors());
        else fullData.setExecutors("не назначен");


        fullData.setCommits(commits);

        fullData.setCreatedAt(order.getCreatedAt().format(AppFormatter.getFormatter()));

        if(order.getCompleted()!=null) fullData.setCompletedAt(order.getCompleted().format(AppFormatter.getFormatter()));
        else  fullData.setCompletedAt("заказ не завершён");
        fullData.setAssignedAt(exec.getAssignedAt().format(AppFormatter.getFormatter()));
        fullData.setProcessId(exec.getId());

        if(exec.getAcceptedAt()!=null) fullData.setAcceptedAt(exec.getAcceptedAt().format(AppFormatter.getFormatter()));
        else fullData.setAcceptedAt("пока не принят");

        if(exec.getFinishedAt()!=null) fullData.setFinishedAt(exec.getFinishedAt().format(AppFormatter.getFormatter()));
        else fullData.setFinishedAt("процесс не завешён");








        return fullData;

    }
}
