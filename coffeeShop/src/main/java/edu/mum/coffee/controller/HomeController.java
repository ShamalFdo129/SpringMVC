package edu.mum.coffee.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.mum.coffee.domain.Account;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.AccountService;
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
}
