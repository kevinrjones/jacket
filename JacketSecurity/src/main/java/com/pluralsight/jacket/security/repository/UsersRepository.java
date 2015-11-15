package com.pluralsight.jacket.security.repository;

import org.springframework.data.repository.CrudRepository;

import com.pluralsight.jacket.security.data.models.User;

public interface UsersRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}