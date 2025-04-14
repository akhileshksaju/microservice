package com.aksprojects.inventory_service.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aksprojects.inventory_service.Repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

  private final InventoryRepository inventoryRepository;

  @Transactional(readOnly = true)
  public boolean isAvailable(String skuCode){
    return inventoryRepository.findBySkuCode(skuCode).isPresent();
  }





}
