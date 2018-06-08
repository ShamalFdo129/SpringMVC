package edu.mum.coffee.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping({"/", "/index", "/home"})
	public String homePage(Model model) {
		List<Product> products=productService.getAllProduct();
		model.addAttribute("products", products);
		return "home";
	}

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}
}
