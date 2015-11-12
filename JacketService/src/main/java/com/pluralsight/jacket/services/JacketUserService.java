package com.pluralsight.jacket.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.pluralsight.jacket.models.AuthenticatedUser;
import com.pluralsight.jacket.models.JacketUser;

public interface JacketUserService extends UserDetailsService {
    public AuthenticatedUser registerNewUserAccount(JacketUser accountDto) throws EmailExistsException;
}
