package com.aksprojects.order_service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.aksprojects.order_service.Controller.OrderControler;
import com.aksprojects.order_service.DTO.OrderLineItemDTO;
import com.aksprojects.order_service.DTO.OrderRequest;
import com.aksprojects.order_service.Model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Testcontainers
class OrderServiceApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private OrderControler orderControler;

	@Container
	static MySQLContainer<?> mySQLContainer = new MySQLContainer("mysql:8.0.32")
																															.withDatabaseName("test")
																															.withUsername("root")
																															.withPassword("root");
	
	@DynamicPropertySource
	static void setDataSourceProperties(DynamicPropertyRegistry registry){
		registry.add("spring.datasource.url",mySQLContainer::getJdbcUrl);
		registry.add("spring.datasource.username",mySQLContainer::getUsername);
		registry.add("spring.datasource.password", mySQLContainer::getPassword);
	}


	

	@Test
	void contextLoads() throws Exception {

			OrderRequest orderRequest = OrderRequest.builder()
																	.orderLineItems(List.of(OrderLineItemDTO.builder().skuCode("123")
																	.price(BigDecimal.valueOf(123.45))
																	.quantity(10).build()))
																	.build();
		String objeString = objectMapper.writeValueAsString(orderRequest);

			
			mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
																						.contentType(MediaType.APPLICATION_JSON)
																						.content(objeString))
																						.andDo(print());

			

	}

}
