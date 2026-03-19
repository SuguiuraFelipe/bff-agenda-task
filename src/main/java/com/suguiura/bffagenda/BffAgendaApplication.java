package com.suguiura.bffagenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BffAgendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffAgendaApplication.class, args);
	}

}
