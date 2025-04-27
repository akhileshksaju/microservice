package com.aksprojects.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aksprojects.Model.OrderProcessing;

@Repository
public interface OrderProcessingRepository extends JpaRepository<OrderProcessing,Long>{

  List<OrderProcessing> findAllByisProcessedFalse();

}
