package com.shamal.coffee.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shamal.coffee.domain.Product;
import com.shamal.coffee.service.ProductService;

@RestController
@RequestMapping("/product-api")
public class ProductController {
	
	@Autowired
	ProductService productService; 
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(){
		return new ResponseEntity<List<Product>>(productService.getAllProduct(),HttpStatus.OK);
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") int id){
		return new ResponseEntity<Product>(productService.getProduct(id),HttpStatus.OK);
	}
	
	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		return new ResponseEntity<Product>(productService.save(product),HttpStatus.CREATED);
	}
	
	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		return new ResponseEntity<Product>(productService.save(product),HttpStatus.OK);
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id){
		
		HttpHeaders header= new HttpHeaders();
		
		Product product =productService.getProduct(id);
		if(product==null)
			return new ResponseEntity<Void>(header,HttpStatus.NOT_FOUND);
		
		productService.delete(product);
		return new ResponseEntity<Void>(header,HttpStatus.OK);
		
	}
	
	
}
