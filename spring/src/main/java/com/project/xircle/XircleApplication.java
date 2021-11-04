package com.project.xircle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class XircleApplication {
	public static void main(String[] args) {
		SpringApplication.run(XircleApplication.class, args);
	}
}
