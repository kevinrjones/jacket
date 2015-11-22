package com.pluralsight.security.filter;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pluralsight.jacketweb.viewmodels.AuthenticationUser;


@Named
public class JacketAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {

	@Autowired
	public JacketAuthenticationFilter(AuthenticationManager authenticationManager) {
		setAuthenticationManager(authenticationManager);
	}

	@Autowired
	private transient Validator validator;

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getServletPath());
		
		if(request.getServletPath().equals("/account/login") && request.getMethod().equals("POST"))
			return true;
		else
			return super.requiresAuthentication(request, response);
	};

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		
		AuthenticationUser loginForm = new AuthenticationUser();
		loginForm.setEmail(request.getParameter("username"));
		loginForm.setPassword(request.getParameter("password"));

        Errors errors = new BeanPropertyBindingResult(loginForm, "login");
        
        if(!this.isValid(validator, errors, loginForm)) {
        	throw new AuthenticationRequiredFieldsException("validation failed", errors);
        }

        return super.attemptAuthentication(request, response);
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword());
//        Authentication authResult = this.getAuthenticationManager().authenticate(authentication);
//
//        return authResult;
	}
	
	private boolean isValid(Validator validator, Errors errors, AuthenticationUser loginForm) {
		ValidationUtils.invokeValidator(validator, loginForm, errors);
		return !errors.hasErrors();		
	}

	public class AuthenticationRequiredFieldsException extends AuthenticationException {

		private static final long serialVersionUID = -3613393016881542212L;
		private Errors errors;

		public AuthenticationRequiredFieldsException(String msg, Throwable error) {
			super(msg, error);
		}

		public AuthenticationRequiredFieldsException(String msg) {
			super(msg);
		}
		
		public AuthenticationRequiredFieldsException(String msg, Errors errors) {
			super(msg);
			this.errors = errors;
		}

		public Errors getErrors() {
			return errors;
		}
	}
}