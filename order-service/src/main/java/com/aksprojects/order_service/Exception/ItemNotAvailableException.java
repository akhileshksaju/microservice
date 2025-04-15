package com.aksprojects.order_service.Exception;

public class ItemNotAvailableException extends RuntimeException{

  public ItemNotAvailableException(String msg){
    super(msg);
  }

  public ItemNotAvailableException(String msg,Throwable cause){
    super(msg,cause);
  }



}
