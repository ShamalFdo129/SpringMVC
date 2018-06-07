package com.shamal.coffee.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shamal.coffee.domain.Order;
import com.shamal.coffee.domain.Person;
import com.shamal.coffee.domain.Product;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	List<Order> findDistinctOrderByOrderLines_Product(Product product);
	List<Order> findOrderByPerson(Person person);
	List<Order> findOrderByOrderDateBetween(Date minDate, Date maxDate);
	

}
