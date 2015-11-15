package com.pluralsight.jacket.repository;

import javax.inject.Named;

import org.springframework.data.repository.CrudRepository;

import com.pluralsight.jacket.data.models.Entry;

@Named
public interface EntryRepository extends CrudRepository<Entry, Long> {
	
}
