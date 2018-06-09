package edu.mum.coffee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import edu.mum.coffee.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
//        http.authorizeRequests()
//    	.antMatchers("/products/*").hasAuthority("ADMIN")
//    	.antMatchers("/productCreate/*").hasAuthority("ADMIN")
//    	.antMatchers("/product/*").hasAuthority("ADMIN")
//    	.antMatchers("/person/*").hasAuthority("ADMIN")
//    	.antMatchers("/personCreate/*").hasAuthority("ADMIN")
//    	.antMatchers("/orders/").hasAuthority("ADMIN")
//    	.antMatchers("/cart/").hasAuthority("ROLE_USER")
//        .antMatchers("/**").permitAll()
//        .anyRequest().authenticated()
//        .and()
//    .formLogin().loginPage("/login")
//    	.permitAll()
//    	.and()
//    .logout()
//    	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//    	.logoutSuccessUrl("/")
//        .permitAll();
		
		http
        .authorizeRequests()
            .antMatchers("/", "/home", "/index","/register").permitAll()
            .antMatchers("/products/*").hasAuthority("ADMIN")
            .antMatchers("/productCreate/*").hasAuthority("ADMIN")
            .antMatchers("/product/*").hasAuthority("ADMIN")
        	.antMatchers("/person/*").hasAuthority("ADMIN")
        	.antMatchers("/personCreate/*").hasAuthority("ADMIN")
        	.antMatchers("/orders/").hasAuthority("ADMIN")
            .anyRequest().authenticated()
            .and()
        .formLogin().loginPage("/login")
        	.permitAll()
        	.and()
        .logout()
        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        	.logoutSuccessUrl("/")
            .permitAll();
    }

	@Autowired
    private AuthenticationService authProvider;
 
    @Override
    protected void configure(
      AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
}