package com.pluralsight.jacket.article.repository;

import java.util.List;

import javax.inject.Named;

import org.springframework.data.repository.CrudRepository;

import com.pluralsight.jacket.data.models.Article;

@Named
public interface ArticleRepository extends CrudRepository<Article, Long> {
	List<Article> findByUserId(long id);
}
