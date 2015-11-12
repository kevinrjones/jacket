package com.pluralsight.jacket.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.pluralsight.jacket.data.models.User;

public class AuthenticatedUser implements UserDetails {
	private String email;
	private String password;
	private String name;

	public AuthenticatedUser(User user){
		email = user.getEmail();
		name = user.getFirstName() + " " + user.getLastName();
		password = user.getPassword();
	}
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	private static final long serialVersionUID = 5639683223516504866L;

	@Override
	public String getPassword() {
		return password;
	}

}
