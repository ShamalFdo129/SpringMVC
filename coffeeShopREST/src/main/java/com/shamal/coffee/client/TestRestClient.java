package com.shamal.coffee.client;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.shamal.coffee.domain.Product;
import com.shamal.coffee.domain.ProductType;



public class TestRestClient {
	
	private static String serverURL="http://localhost:8080";
	private static RestTemplate  restTemplate =new RestTemplate();
	private static int productId;
	
	public static void testingGetProducts() {
		System.out.println("Test getting Products");
		List<LinkedHashMap> productList=restTemplate.getForObject(serverURL+"/product-api/products", List.class);
		
		System.out.println("Test Getting Products");
		for(LinkedHashMap map: productList) {
			System.out.println(String.format("Product Name: %s , Price: %f , Product Type: %s",
					map.get("productName"),map.get("price"),map.get("productType")));
		}
	}
	
	public static void testingGetProduct(int id) {
		Product product=restTemplate.getForObject(serverURL+"/product-api/products/"+id, Product.class);
		System.out.println("\nTest Getting one Product");
		System.out.println(product);
		
	}
	
	public static void tesingCreateProduct() {
		
		Product product=new Product();
		product.setProductName("Black Coffe");
		product.setPrice(2.34);
		product.setProductType(ProductType.BREAKFAST);
		product.setDescription("This is english black coffe");
		
		ResponseEntity<Product> newProduct=restTemplate.postForEntity(serverURL+"/product-api/product/",product, Product.class);
		productId=newProduct.getBody().getId();
		System.out.println("\nTest Creating a product");
		System.out.println(product);
		
	}
	
	public static void tesingDeleteproduct() {
		System.out.println("\nTest Deleting a product");
		restTemplate.delete(serverURL+"/product-api/product/"+productId);
	}
	
	
	public static void testingUpdateproduct() {
		Product product=restTemplate.getForObject(serverURL+"/product-api/products/"+productId, Product.class);
		product.setDescription("This is new American english black coffe");
		
		System.out.println("\nTest Updating a product");
		restTemplate.put(serverURL+"/product-api/product/",product,product);
			
	}
	
	

}
