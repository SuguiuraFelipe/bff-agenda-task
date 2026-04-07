package com.suguiura.bffagenda.business.dto;


import com.suguiura.bffagenda.business.dto.out.TaskDTOResponse;
import com.suguiura.bffagenda.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient client;

    public void sendEmail(TaskDTOResponse dto) {
         client.sendEmail(dto);
    }
}
