package com.aksprojects.order_service.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aksprojects.order_service.DTO.OrderRequest;
import com.aksprojects.order_service.Service.OrderService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderControler {
  private final OrderService orderService;
      

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String placeOrder (@RequestBody OrderRequest orderRequest) {
      //TODO: process POST request
      orderService.placeOrder(orderRequest);

      return "Order Placed Success Fully";
  }
  

}
