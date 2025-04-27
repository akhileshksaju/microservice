package com.aksprojects.Service;

import java.util.List;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.aksprojects.DTO.EventType;
import com.aksprojects.Model.OrderProcessing;
import com.aksprojects.Repository.OrderProcessingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderPublisherService {

  private final OrderProcessingRepository orderProcessingRepository;
  private final KafkaTemplate<String,OrderProcessing> kafkaTemplate;

  @Scheduled(fixedDelay = 6000)
  public void getMessage(){

    EventType eventType = EventType.NOTIFICATION;

    List<OrderProcessing> lsitToProcess=orderProcessingRepository.findAllByisProcessedFalse();
    lsitToProcess.stream().forEach(oreder->{
      kafkaTemplate.send(eventType.getEventType(),oreder);
    });


  }

}
