package com.aksprojects.order_service.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ItemNotAvailableException.class)
  public ResponseEntity<Map<String,String>> handleItemNotFoundException(ItemNotAvailableException ex){
     Map<String,String> body = new HashMap<>();
     body.put("message", ex.getMessage());
     return new ResponseEntity<Map<String, String>>(body,HttpStatus.NOT_FOUND);
  }

}
