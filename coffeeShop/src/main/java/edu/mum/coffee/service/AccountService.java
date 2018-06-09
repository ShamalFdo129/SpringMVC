package edu.mum.coffee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.coffee.domain.Account;
import edu.mum.coffee.domain.Order;
import edu.mum.coffee.repository.AccountRepository;

@Service
@Transactional
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Account save(Account account){
		return accountRepository.save(account);
	}

}
