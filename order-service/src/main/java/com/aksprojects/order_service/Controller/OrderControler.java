package com.aksprojects.order_service.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aksprojects.order_service.DTO.OrderRequest;
import com.aksprojects.order_service.Service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderControler {
  private final OrderService orderService;
      

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @CircuitBreaker(name="inventory",fallbackMethod = "fallBackMethod")
  @TimeLimiter(name="inventorytl")
  @Retry(name="inventoryr")
  public CompletableFuture<String> placeOrder (@RequestBody OrderRequest orderRequest) {
      //TODO: process POST request
        return CompletableFuture.supplyAsync(()-> orderService.placeOrder(orderRequest));


      // return "Order Placed Success Fully";
  }


  public CompletableFuture<String> fallBackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
    return CompletableFuture.supplyAsync(()->"somthing went wrong");
  }
  

}
