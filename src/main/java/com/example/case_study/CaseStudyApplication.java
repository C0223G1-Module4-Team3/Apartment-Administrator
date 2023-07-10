package com.example.case_study;

import com.example.case_study.schedule.Schedule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;

@SpringBootApplication
@EnableScheduling
public class CaseStudyApplication {

    public static void main(String[] args) throws MessagingException {
        SpringApplication.run(CaseStudyApplication.class, args);
    }
}
