package com.pluralsight.jacket.repository;

import org.springframework.data.repository.CrudRepository;

import com.pluralsight.jacket.data.models.User;

public interface UsersRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}