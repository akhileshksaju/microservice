package com.aksprojects.inventory_service.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aksprojects.inventory_service.DTO.InventoryResponse;
import com.aksprojects.inventory_service.Repository.InventoryRepository;
import com.aksprojects.inventory_service.model.Inventory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

  private final InventoryRepository inventoryRepository;

  @Transactional(readOnly = true)
  public List<InventoryResponse> isAvailable(List<String> skuCodes) throws InterruptedException{

    Thread.sleep(10000);
    log.info("thread is sleeping");
    

    List<Inventory> inStockItems = inventoryRepository.findBySkuCodeIn(skuCodes);
    // inStockItems.stream().forEach(item->System.out.println(item.getSkuCode()));
    // log.info(inStockItems.isEmpty()?"empty":"not Empty");


   return inStockItems.stream().map(item ->InventoryResponse.builder().skuCode(item.getSkuCode())
                                                                .isAvailable(item.getQuantity()>0)
                                                                .build()).toList();


  }





}
