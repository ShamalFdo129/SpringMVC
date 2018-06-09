package edu.mum.coffee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.coffee.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	
}
