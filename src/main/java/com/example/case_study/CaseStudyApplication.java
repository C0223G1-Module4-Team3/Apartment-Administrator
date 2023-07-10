package com.example.case_study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.mail.MessagingException;

@SpringBootApplication
@EnableScheduling
public class CaseStudyApplication {

	public static void main(String[] args) throws MessagingException {
		SpringApplication.run(CaseStudyApplication.class, args);
	}

}
