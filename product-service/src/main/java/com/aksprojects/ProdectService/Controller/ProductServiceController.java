package com.aksprojects.ProdectService.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aksprojects.ProdectService.DTO.ProductRequest;
import com.aksprojects.ProdectService.DTO.ProductResponse;
import com.aksprojects.ProdectService.Service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductServiceController {

  
  private final ProductService productService;

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public void CreateProduct(@RequestBody ProductRequest productRequest) {
      //TODO: process POST request
      log.info("controller:"+productRequest.getDisc());

      productService.createProduct(productRequest);





      
      
  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public List<ProductResponse> getAllProduct() {
      return productService.getAllProduct();
  }
  
  
  

}
