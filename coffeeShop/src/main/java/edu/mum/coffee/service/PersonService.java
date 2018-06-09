package edu.mum.coffee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.repository.PersonRepository;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Person savePerson(Person person) {
		return personRepository.save(person);
	}

	public Person findByEmail(String email) {
		List<Person> personList=personRepository.findByEmail(email);
		if(personList.size()>0)
			return personList.get(0);
		else 
			return null;
	}

	public Person findById(Long id) {
		return personRepository.findOne(id);
	}

	public void removePerson(Person person) {
		personRepository.delete(person);
	}
	
	public List<Person> getAllPeople() {
		return personRepository.findAll();
	}

}
