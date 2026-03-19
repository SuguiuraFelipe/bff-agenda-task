package com.suguiura.bffagenda.business;


import com.suguiura.bffagenda.business.dto.in.TaskDTORequest;
import com.suguiura.bffagenda.business.dto.out.TaskDTOResponse;
import com.suguiura.bffagenda.infrastructure.client.TaskClient;
import com.suguiura.bffagenda.infrastructure.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskClient client;

    public TaskDTOResponse saveTask(String token, TaskDTORequest dto) {
        return client.saveTask(dto, token);
    }

    public List<TaskDTOResponse> searchTaskDate(LocalDateTime startDate, LocalDateTime endDate,
                                                String token) {
        return client.searchTaskListPerPeriod(startDate, endDate, token);
    }

    public List<TaskDTOResponse> searchTaskEmail(String token){
        return client.searchTaskByEmail(token);
    }

    public void deleteTaskById(String id, String token){
        client.deleteTaskById(id, token);
    }

    public TaskDTOResponse changeStatus(NotificationStatusEnum status, String id, String token){
        return client.changeStatusNotification(status, id, token);
    }

    public TaskDTOResponse updateTask(TaskDTORequest dto, String id, String token){
        return client.updateTask(dto, id, token);
    }
}
