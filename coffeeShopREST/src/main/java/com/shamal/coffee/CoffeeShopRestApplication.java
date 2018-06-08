package com.shamal.coffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shamal.coffee.client.TestRestClient;

@SpringBootApplication
public class CoffeeShopRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeShopRestApplication.class, args);
		
		//Testing get List of products
		TestRestClient.testingGetProducts();
		
		//Testing get one products
		TestRestClient.testingGetProduct(322);
		
		//Testing Create a product
		TestRestClient.tesingCreateProduct();
		
		//Testing Update a product
		//TestRestClient.testingUpdateproduct();
		
		//Testing Delete a product
		//TestRestClient.tesingDeleteproduct();
	}
}
