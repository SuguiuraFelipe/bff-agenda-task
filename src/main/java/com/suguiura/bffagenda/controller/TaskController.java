package com.suguiura.bffagenda.controller;


import com.suguiura.bffagenda.business.TaskService;
import com.suguiura.bffagenda.business.dto.in.TaskDTORequest;
import com.suguiura.bffagenda.business.dto.out.TaskDTOResponse;
import com.suguiura.bffagenda.infrastructure.enums.NotificationStatusEnum;
import com.suguiura.bffagenda.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Register and update user tasks")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Save Tasks", description = "Create a new Task")
    @ApiResponse(responseCode = "200", description = "Task save with success")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<TaskDTOResponse> saveTask(@RequestBody TaskDTORequest dto, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(taskService.saveTask(token ,dto));
    }

    @GetMapping("/events")
    @Operation(summary = "Search tasks per period", description = "Search Tasks")
    @ApiResponse(responseCode = "200", description = "Task founded with success")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<List<TaskDTOResponse>> searchTaskListPerPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate,
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(taskService.searchTaskDate(startDate, endDate, token));
    }

    @GetMapping
    @Operation(summary = "Search tasks list per email", description = "Search Tasks")
    @ApiResponse(responseCode = "200", description = "Tasks founded with success")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<List<TaskDTOResponse>> searchTaskByEmail(@RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(taskService.searchTaskEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Delete task per id", description = "Delete Tasks")
    @ApiResponse(responseCode = "200", description = "Tasks deleted with success")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<Void> deleteTaskById(@RequestParam("id") String id, @RequestHeader(name = "Authorization", required = false) String token){
        taskService.deleteTaskById(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Update status task per id", description = "Update status Tasks")
    @ApiResponse(responseCode = "200", description = "Tasks updated status with success")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<TaskDTOResponse> changeStatusNotification(@RequestParam("status") NotificationStatusEnum statusEnum,
                                                                    @RequestParam("id") String id,
                                                                    @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(taskService.changeStatus(statusEnum, id, token));
    }

    @PutMapping
    @Operation(summary = "Update task per id", description = "Update Tasks")
    @ApiResponse(responseCode = "200", description = "Tasks updated with success")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<TaskDTOResponse> updateTask(@RequestBody TaskDTORequest dto, @RequestParam("id") String id,
                                                      @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(taskService.updateTask(dto, id, token));
    }
}
