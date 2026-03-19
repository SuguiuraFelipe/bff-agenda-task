package com.suguiura.bffagenda.infrastructure.client;

import com.suguiura.bffagenda.business.dto.in.TaskDTORequest;
import com.suguiura.bffagenda.business.dto.out.TaskDTOResponse;
import com.suguiura.bffagenda.infrastructure.enums.NotificationStatusEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tasks", url = "${tasks.url}")
public interface TaskClient {

    @PostMapping
    TaskDTOResponse saveTask(@RequestBody TaskDTORequest dto, @RequestHeader("Authorization") String token);

    @GetMapping("/events")
    List<TaskDTOResponse> searchTaskListPerPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TaskDTOResponse> searchTaskByEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deleteTaskById(@RequestParam("id") String id, @RequestHeader("Authorization") String token);

    @PatchMapping
    TaskDTOResponse changeStatusNotification(@RequestParam("status") NotificationStatusEnum statusEnum,
                                             @RequestParam("id") String id,
                                             @RequestHeader("Authorization") String token);

    @PutMapping
    TaskDTOResponse updateTask(@RequestBody TaskDTORequest dto, @RequestParam("id") String id, @RequestHeader("Authorization") String token);
}
