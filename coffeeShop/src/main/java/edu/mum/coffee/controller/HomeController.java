package edu.mum.coffee.controller;


import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import edu.mum.coffee.OrderServiceTest;
import edu.mum.coffee.domain.Account;
import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.AccountService;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	PersonService personService;
	
	@Autowired
	OrderService orderService;
	
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
	
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}
	
	@PostMapping("/register")
	public String createAccount(Account account,Model model) {
		
		//Checking user is in the system
		Person person= personService.findByEmail(account.getPerson().getEmail());
		if(person==null) {
			model.addAttribute("error", "The user is not in the system, Please contact Admin");
			return "errorPage";
		}
		else {
			account.setPerson(person);
			accountService.save(account);
		}
		return "redirect:/home";
	}
	
	@GetMapping(value={"/login"})
	public String login(){
		return "login";
	}
	
	@PostMapping("/order/{id}")
	public String createOrder(@PathVariable int id,HttpSession httpSession) {
		Person person;
		Order order;
		Orderline orderLine;
		person=personService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		
		if(httpSession.getAttribute("OrderId")==null) {
			if(person!=null) {
				order=new Order();
				order.setOrderDate(new Date());
				order.setPerson(person);
				
				orderLine=new Orderline();
				orderLine.setOrder(order);
				orderLine.setQuantity(1);
				orderLine.setProduct(productService.getProduct(id));
				
				order.addOrderLine(orderLine);
				order=orderService.save(order);
				httpSession.setAttribute("OrderId", order.getId());
			}
		}
		else {
			if(person!=null) {
				order=orderService.findById((int)httpSession.getAttribute("OrderId"));
				
				orderLine=new Orderline();
				orderLine.setOrder(order);
				orderLine.setQuantity(1);
				orderLine.setProduct(productService.getProduct(id));
				order.addOrderLine(orderLine);
				order=orderService.save(order);
				
			}
		}
		
		
		return "redirect:/home";
	}
}
