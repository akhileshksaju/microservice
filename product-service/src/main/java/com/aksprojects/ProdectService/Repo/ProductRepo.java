package com.aksprojects.ProdectService.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aksprojects.ProdectService.Model.Product;

@Repository
public interface ProductRepo extends MongoRepository<Product,String> {

}
