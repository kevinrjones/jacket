package com.pluralsight.jacket.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.pluralsight.jacket.security.service.models.AuthenticatedUser;
import com.pluralsight.jacket.security.service.models.JacketUser;

public interface JacketUserService extends UserDetailsService {
    public AuthenticatedUser registerNewUserAccount(JacketUser accountDto) throws EmailExistsException;
}
