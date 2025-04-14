package com.aksprojects.order_service.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aksprojects.order_service.DTO.OrderLineItemDTO;
import com.aksprojects.order_service.DTO.OrderRequest;
import com.aksprojects.order_service.Model.Order;
import com.aksprojects.order_service.Model.OrderLineItem;
import com.aksprojects.order_service.Repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

  
  private final OrderRepository orderRepository;

  public void placeOrder(OrderRequest orderRequest){
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());

    List<OrderLineItem> orderLineItems =  orderRequest.getOrderLineItems().stream().map(this::mapToDto).toList();

    order.setOrderLineItemsList(orderLineItems);
    orderRepository.save(order);

  }

  public OrderLineItem mapToDto(OrderLineItemDTO orderLineItem){
    return OrderLineItem.builder().price(orderLineItem.getPrice())
    .quantity(orderLineItem.getQuantity())
    .skuCode(orderLineItem.getSkuCode())
    .build();
  }
}
