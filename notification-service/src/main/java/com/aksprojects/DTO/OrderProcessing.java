package com.aksprojects.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProcessing {

  
  private Long id;

  private String skuCode;
  private Boolean isProcessed;
  

}
