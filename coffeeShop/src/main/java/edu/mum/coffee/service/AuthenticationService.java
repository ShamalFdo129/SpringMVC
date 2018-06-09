package edu.mum.coffee.service;

import javax.naming.AuthenticationException;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import edu.mum.coffee.domain.Account;

@Component
public class AuthenticationService implements AuthenticationProvider {
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public Authentication authenticate(Authentication authentication) {
		String email = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		if(isAdmin(email, password)) {
			return adminAuth(email, password);
		}
		else if( accountService.getAccount(email, password)!=null)
		{
			return personAuth(email, password);
		}
		else {
			throw new BadCredentialsException("Authentication failed for user = " + email);
		}
	}

	private boolean isAdmin(String email, String password) {
		return email.equals("shamalf1@gmail.com") && password.equals("abc123");
	}

	private Authentication adminAuth(String name, String password) {
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
		grantedAuths.add(new SimpleGrantedAuthority("ADMIN"));
		Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
		return auth;
	}

	private Authentication personAuth(String name, String password) {
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
		Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
		return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	

}
