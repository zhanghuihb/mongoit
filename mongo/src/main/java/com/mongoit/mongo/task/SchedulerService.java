package com.mongoit.mongo.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SchedulerService{

    @Scheduled(cron = "0 0/1 * * * ?")
    public void execute() {
        System.out.println("-----------------------------------------------------------------------------------------------");
        for (int i = 0; i < 100000; i++) {
            System.out.println("+++++++++++++++++++++++++++++++++++++++"+i+i);
        }
        for (int i = 0; i < 100000; i++) {
            System.out.println("--------------------------"+i+i);
        }
        for (int i = 0; i < 100000; i++) {
            System.out.println("================="+i+i);
        }
        for (int i = 0; i < 100000; i++) {
            System.out.println("*********+"+i+i);
        }
        for (int i = 0; i < 100000; i++) {
            System.out.println("@@@@+"+i+i);
        }
    }
}
