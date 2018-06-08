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
import org.springframework.web.bind.annotation.RestController;

import com.shamal.coffee.domain.Order;
import com.shamal.coffee.service.OrderService;



@RestController
@RequestMapping("/order-api")
public class OrderController {
	
	@Autowired
	OrderService orderService; 
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getOrders(){
		return new ResponseEntity<List<Order>>(orderService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") int id){
		return new ResponseEntity<Order>(orderService.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/order")
	public ResponseEntity<Order> createOrder(@RequestBody Order order){
		return new ResponseEntity<Order>(orderService.save(order),HttpStatus.CREATED);
	}
	
	@PutMapping("/order")
	public ResponseEntity<Order> updateOrder(@RequestBody Order person){
		return new ResponseEntity<Order>(orderService.save(person),HttpStatus.OK);
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") int id){
		
		HttpHeaders header= new HttpHeaders();
		
		Order order =orderService.findById(id);
		if(order==null)
			return new ResponseEntity<Void>(header,HttpStatus.NOT_FOUND);
		
		orderService.delete(order);
		return new ResponseEntity<Void>(header,HttpStatus.OK);
		
	}


}
