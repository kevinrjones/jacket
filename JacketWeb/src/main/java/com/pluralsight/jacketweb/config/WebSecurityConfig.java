package com.pluralsight.jacketweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.pluralsight.security.filter.JacketAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JacketAuthenticationFilter jacketAuthenticationFilter;
	
//	@Bean
//	JacketAuthenticationFilter jacketAuthenticationFilter() throws Exception {
//		JacketAuthenticationFilter jacketAuthenticationFilter = new JacketAuthenticationFilter();
//		jacketAuthenticationFilter.setAuthenticationManager(authenticationManager());
//		return jacketAuthenticationFilter;
//	}

	@Autowired
	private AuthenticationManagerBuilder auth;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		    //.addFilterBefore(jacketAuthenticationFilter, AbstractPreAuthenticatedProcessingFilter.class)
		    .authorizeRequests()
		    .antMatchers("/resources/**", "/account/register").permitAll()
		    .anyRequest()
		    .authenticated()
		 .and()
		 	.formLogin()
		 	.loginPage("/account/login")
		 	.permitAll()
		 .and()
		 	.logout()
		 	.logoutUrl("/account/logout")
			.permitAll();
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	}

}