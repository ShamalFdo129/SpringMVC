package edu.mum.coffee.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.coffee.domain.Account;
import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.repository.AccountRepository;

@Service
@Transactional
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account save(Account account) {
		return accountRepository.save(account);
	}

	public Account getAccount(String email, String password) {
		List<Account> accountlist =accountRepository.findAll().stream().
				filter(a->a.getPassword().equals(password)).filter(a->a.getPerson().getEmail().equals(email))
				.collect(Collectors.toList());
		if(accountlist.size()>0)
			return accountlist.get(0);
		else
			return null;
		
	}

}
