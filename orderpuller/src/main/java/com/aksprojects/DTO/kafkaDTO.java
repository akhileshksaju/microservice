package com.aksprojects.DTO;

import com.aksprojects.Model.OrderProcessing;


public record kafkaDTO(String eventType,OrderProcessing orderProcessing) {

  
}
