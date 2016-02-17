package com.pluralsight.jacket.entry.repository;

import java.util.List;

import javax.inject.Named;

import org.springframework.data.repository.CrudRepository;

import com.pluralsight.jacket.data.models.Entry;

@Named
public interface ArticleRepository extends CrudRepository<Entry, Long> {
	List<Entry> findByUserId(long id);
}
