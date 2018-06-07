package com.shamal.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shamal.coffee.domain.Product;
import com.shamal.coffee.service.ProductService;

@RestController
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/api/student")
	public Product homePage() {
		return  productService.getProduct(322);
	}

	
}
