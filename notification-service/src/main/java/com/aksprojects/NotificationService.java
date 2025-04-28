package com.aksprojects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.aksprojects.DTO.OrderProcessing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class NotificationService {

    public static void main(String[] args) {
       SpringApplication.run(NotificationService.class, args);
    }

    @KafkaListener(topics = "notification-type")
    public void notificationService(OrderProcessing orderProcessing){

        log.info(orderProcessing.getSkuCode());

    }


     
}