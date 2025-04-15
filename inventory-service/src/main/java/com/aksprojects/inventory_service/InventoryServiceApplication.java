package com.aksprojects.inventory_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.aksprojects.inventory_service.Repository.InventoryRepository;
import com.aksprojects.inventory_service.model.Inventory;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return 	args ->{
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone 13");
			inventory1.setQuantity(10);
			inventoryRepository.save(inventory1);
		};
	}

}
