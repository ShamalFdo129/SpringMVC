package edu.mum.coffee.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.ProductService;

@Controller
public class AdminController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/products")
	public String productListPage(Model model) {
		List<Product> products=productService.getAllProduct();
		model.addAttribute("products", products);
		return "productList";
	}
	
	@GetMapping("/products/{id}")
	public String productPage(@PathVariable int id, Model model) {
		Product product= productService.getProduct(id);
		model.addAttribute("product", product);
		return "productPage";
	}
	
	@GetMapping("/productCreate")
	public String createProductPage() {
		return "createProductPage";
	}
	
	@PostMapping("/product")
	public String saveProduct(Product product) {
		productService.save(product);
		return "redirect:/products";
	}
	
	@PostMapping("/product/{id}")
	public String deleteproduct(@PathVariable int id ) {
		productService.delete(id);
		return "redirect:/products";
	}


}
