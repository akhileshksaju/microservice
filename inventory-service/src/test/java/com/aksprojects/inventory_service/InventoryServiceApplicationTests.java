package com.aksprojects.inventory_service;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.aksprojects.inventory_service.Repository.InventoryRepository;
import com.aksprojects.inventory_service.Service.InventoryService;
import com.aksprojects.inventory_service.model.Inventory;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class InventoryServiceApplicationTests {

	@MockitoBean
	private InventoryService inventoryService;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private MockMvc mockMvc;

	@Container
	static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.32")
																								.withDatabaseName("testDb")
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
		Inventory inventory = Inventory.builder()
													.skuCode("123")
													.quantity(10).build();

		inventoryRepository.save(inventory);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory/{skuCode}", "123"))
																					.andExpect(status().isOk());

		
	}

}
