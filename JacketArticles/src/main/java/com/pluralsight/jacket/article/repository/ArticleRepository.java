package com.pluralsight.jacket.article.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pluralsight.jacket.data.models.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
	List<Article> findByUserId(long id);
	List<Article> findByTitle(String title);
}
