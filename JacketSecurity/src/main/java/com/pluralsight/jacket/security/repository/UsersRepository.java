package com.pluralsight.jacket.security.repository;

import org.springframework.data.repository.CrudRepository;

import com.pluralsight.jacket.data.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}