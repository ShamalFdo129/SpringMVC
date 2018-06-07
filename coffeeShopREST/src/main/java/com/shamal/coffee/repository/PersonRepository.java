package com.shamal.coffee.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shamal.coffee.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	public List<Person> findByEmail(String email);
	
}
