package com.aksprojects.inventory_service.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aksprojects.inventory_service.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long>{

  Optional<Inventory> findBySkuCode(String skuCode);

}
