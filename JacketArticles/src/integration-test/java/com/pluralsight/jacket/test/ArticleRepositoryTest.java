package com.pluralsight.jacket.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pluralsight.jacket.article.repository.ArticleRepository;
import com.pluralsight.jacket.data.models.Article;
import com.pluralsight.repository.AbstractTest;

public class ArticlesRepositoryTest extends AbstractTest {
	@Autowired
	ArticleRepository repository;
	private int count;

	@Test
	public void shouldFindAllArticles() {

		Iterable<Article> articles = repository.findAll();

		articles.forEach(a -> {			
			count++;
		});
		assertThat(articles).isNotNull();
		assertThat(count).isEqualTo(2);
	}

}
