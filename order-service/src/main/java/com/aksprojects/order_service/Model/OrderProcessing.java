package com.aksprojects.order_service.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProcessing {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String skuCode;
  private Boolean isProcessed;
  @PrePersist
  public void PrePersist(){
    if(isProcessed==null){
      isProcessed=false;
    }
  }
  

}
