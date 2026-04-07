package com.suguiura.bffagenda.infrastructure.client;

import com.suguiura.bffagenda.business.dto.out.TaskDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "notification", url = "${notification.url}")
public interface EmailClient {

    @PostMapping
    void sendEmail(@RequestBody TaskDTOResponse dto);
}
