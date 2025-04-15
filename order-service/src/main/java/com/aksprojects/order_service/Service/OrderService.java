package com.aksprojects.order_service.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.aksprojects.order_service.DTO.InventoryResponse;
import com.aksprojects.order_service.DTO.OrderLineItemDTO;
import com.aksprojects.order_service.DTO.OrderRequest;
import com.aksprojects.order_service.Exception.ItemNotAvailableException;
import com.aksprojects.order_service.Model.Order;
import com.aksprojects.order_service.Model.OrderLineItem;
import com.aksprojects.order_service.Repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

  
  private final OrderRepository orderRepository;
  private final WebClient webClient;

  public void placeOrder(OrderRequest orderRequest){
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());

    List<OrderLineItem> orderLineItems =  orderRequest.getOrderLineItems().stream().map(this::mapToDto).toList();

    order.setOrderLineItemsList(orderLineItems);


    List<String> skuCodeList = orderLineItems.stream().map(item->item.getSkuCode()).toList();


    //need to call to inventory service to check the stock over there 
    InventoryResponse[] result = webClient.get()
    .uri(uriBuilder -> uriBuilder
            .scheme("http")
            .host("localhost")
            .port(8082)
            .path("/api/inventory")
            .queryParam("skuCodes", skuCodeList)
            .build())
    .retrieve()
    .bodyToMono(InventoryResponse[].class)
    .block();  // <-- this gives you InventoryResponse[]

    Arrays.stream(result).forEach(item ->log.info(item.isAvailable()?"y":"n"));

    boolean allAvailable = result.length > 0 &&Arrays.stream(result).allMatch(InventoryResponse::isAvailable);
    System.err.println(allAvailable);

    log.info(allAvailable?"yes":"no");

    if (allAvailable) {
      orderRepository.save(order);

    }
    else{
      throw new ItemNotAvailableException("your item currently unavailable");
    }


  }

  public OrderLineItem mapToDto(OrderLineItemDTO orderLineItem){
    return OrderLineItem.builder().price(orderLineItem.getPrice())
    .quantity(orderLineItem.getQuantity())
    .skuCode(orderLineItem.getSkuCode())
    .build();
  }
}
