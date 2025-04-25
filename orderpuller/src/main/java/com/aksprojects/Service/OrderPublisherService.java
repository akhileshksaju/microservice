package com.aksprojects.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OrderPublisherService {

  @Scheduled(fixedDelay = 6000)
  public void getMessage(){

    


  }

}
