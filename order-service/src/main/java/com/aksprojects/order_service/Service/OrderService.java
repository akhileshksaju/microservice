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

import io.micrometer.tracing.Span;
import io.opentelemetry.api.trace.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import zipkin2.internal.Trace;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {


  private final io.micrometer.tracing.Tracer trace;
  
  private final OrderRepository orderRepository;
  private final WebClient.Builder webClientBuilder;


  public String  placeOrder(OrderRequest orderRequest){
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());

    List<OrderLineItem> orderLineItems =  orderRequest.getOrderLineItems().stream().map(this::mapToDto).toList();

    order.setOrderLineItemsList(orderLineItems);


    List<String> skuCodeList = orderLineItems.stream().map(item->item.getSkuCode()).toList();


   
    Span inventoryServiceLookup = trace.nextSpan().name("inventory-service-lookup");
    //need to call to inventory service to check the stock over there
    trace.withSpan(inventoryServiceLookup.start()); 
    InventoryResponse[] result = webClientBuilder.build().get()
    .uri("http://inventory-Service/api/inventory",uriBuilder -> uriBuilder
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
      return "order processed";

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
