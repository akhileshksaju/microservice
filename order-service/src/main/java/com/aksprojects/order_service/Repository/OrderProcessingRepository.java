package com.aksprojects.order_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aksprojects.order_service.Model.OrderProcessing;

public interface OrderProcessingRepository extends JpaRepository<OrderProcessing,Long> {

}
