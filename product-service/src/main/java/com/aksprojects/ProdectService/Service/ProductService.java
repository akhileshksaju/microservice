package com.aksprojects.ProdectService.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aksprojects.ProdectService.DTO.ProductRequest;
import com.aksprojects.ProdectService.DTO.ProductResponse;
import com.aksprojects.ProdectService.Model.Product;
import com.aksprojects.ProdectService.Repo.ProductRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
  
  @Autowired
  ProductRepo productRepo;

 public void createProduct(ProductRequest propRequest){
      Product product = Product.builder()
      .name(propRequest.getName())
      .disc(propRequest.getDisc())
      .build();
      log.info(propRequest.getDisc());

      productRepo.save(product);

 }

 public List<ProductResponse> getAllProduct(){

  return productRepo.findAll().stream().map(Product->mapProductt(Product)).toList();
    
   }
  
  private ProductResponse mapProductt(Product product) {
    // TODO Auto-generated method stub
    return ProductResponse.builder().name(product.getName()).disc(product.getDisc()).build();
  }

}
