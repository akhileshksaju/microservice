package com.aksprojects.inventory_service.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aksprojects.inventory_service.DTO.InventoryResponse;
import com.aksprojects.inventory_service.Service.InventoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("api/inventory")
@RequiredArgsConstructor
public class InventoryController {

  private final InventoryService inventoryService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public List<InventoryResponse> getMethodName(@RequestParam("skuCodes") List<String> skuCodes) throws InterruptedException {
      return inventoryService.isAvailable(skuCodes);
  }
  

}
