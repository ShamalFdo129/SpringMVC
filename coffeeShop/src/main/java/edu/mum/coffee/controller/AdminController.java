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

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;

@Controller
public class AdminController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	PersonService personService;
	
	@Autowired
	OrderService orderService;
	
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
	
	@GetMapping("/person")
	public String peopleListPage(Model model) {
		List<Person> people=personService.getAllPeople();
		model.addAttribute("people", people);
		return "peopleList";
	}
	
	@GetMapping("/personCreate")
	public String personCreatePage() {
		return "createPersonPage";
	}
	
	@PostMapping("/person")
	public String savePerson(Person person) {
		personService.savePerson(person);
		return "redirect:/person";
	}
	
	@GetMapping("/orders")
	public String orderListPage(Model model) {
		List<Order> orders=orderService.findAll();
		model.addAttribute("orders", orders);
		return "orderList";
	}
	


}
