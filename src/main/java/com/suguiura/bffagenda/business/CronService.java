package com.suguiura.bffagenda.business;

import com.suguiura.bffagenda.business.dto.EmailService;
import com.suguiura.bffagenda.business.dto.in.LoginDTORequest;
import com.suguiura.bffagenda.business.dto.out.TaskDTOResponse;
import com.suguiura.bffagenda.infrastructure.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CronService {

    private final TaskService service;
    private final EmailService emailService;
    private final UserService userService;

    @Value("${user.email}")
    private String email;
    @Value("${user.password}")
    private String password;

    @Scheduled(cron = "${cron.time}")
    public void nextHourTasks(){
        String token = login(requestDTOConverter());
        LocalDateTime nextHour = LocalDateTime.now().plusHours(1);
        LocalDateTime nextHourPlusFive = LocalDateTime.now().plusHours(1).plusMinutes(5);
        List<TaskDTOResponse> taskList = service.searchTaskDate(nextHour, nextHourPlusFive, token);
        taskList.forEach(task -> {emailService.sendEmail(task);
            service.changeStatus(NotificationStatusEnum.NOTIFIED, task.getId(), token);
        });
    }

    public String login(LoginDTORequest dto){
        return userService.userLogin(dto);
    }

    public LoginDTORequest requestDTOConverter(){
        return LoginDTORequest.builder()
                .email(email)
                .password(password)
                .build();
    }
}
