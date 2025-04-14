package com.aksprojects.ProdectService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.aksprojects.ProdectService.DTO.ProductRequest;
import com.aksprojects.ProdectService.Repo.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.assertions.Assertions;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProdectServiceApplicationTests {

	// @Container
	// static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:8.0.6");

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private MongoTemplate mongoTemplate;


	// @DynamicPropertySource
	// static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
	// 	dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	// }


	@Test
	void checkCreate() throws Exception {

		ProductRequest productRequest= getProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		
				mockMvc.perform(MockMvcRequestBuilders.post("/productService")
																							.contentType(MediaType.APPLICATION_JSON)
																							.content(productRequestString))
																							.andDo(print())
																							.andExpect(status().isCreated());
				Assertions.assertTrue(productRepo.findAll().size()>0);
																							
			}

			@Test
			void checkGetAllProducts() throws Exception{
				mockMvc.perform(MockMvcRequestBuilders.get("/productService")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
				// Assertions.assertTrue(productRepo.findAll().size()==0);
			}
		
		
			private ProductRequest getProductRequest() {
				// TODO Auto-generated method stub
				return ProductRequest.builder()
				.name("iphone")
				.disc("latest iphone")
				.mis("bla bla") 
				.build();


							
			
			
			}

			@AfterEach
			void Cleanup(){
				mongoTemplate.getCollectionNames().forEach(names -> mongoTemplate.getCollection(names).deleteMany(new org.bson.Document()));
			}

	

}
