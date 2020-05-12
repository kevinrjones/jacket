package com.pluralsight.jacket.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.apache.commons.logging.Log;
import org.junit.Before;
import org.junit.Test;

import com.pluralsight.jacket.article.repository.ArticleRepository;
import com.pluralsight.jacket.article.service.ArticleDetailsServiceOnArticleRepository;
import com.pluralsight.jacket.article.service.JacketServiceException;
import com.pluralsight.jacket.article.service.models.AddJacketArticle;
import com.pluralsight.jacket.article.service.models.GetJacketArticle;
import com.pluralsight.jacket.data.models.Article;
import com.pluralsight.jacket.data.models.User;
import com.pluralsight.jacket.security.repository.UsersRepository;

public class JacketArticleServiceOnRepositoryTest {
	ArticleDetailsServiceOnArticleRepository jacketEntryServiceOnRepository;
	ArticleRepository entryRepository;
	UsersRepository usersRepository;
	Log log;
	ArticleDetailsServiceOnArticleRepository service;

	@Before
	public void before() {
		entryRepository = mock(ArticleRepository.class);
		usersRepository = mock(UsersRepository.class);
		log = mock(Log.class);
		service = new ArticleDetailsServiceOnArticleRepository(entryRepository, usersRepository, log);
	}

	@Test
	public void getAllEntries_should_return_all_articles() throws SerialException, SQLException {

		User user = new User();
		Article entry = new Article();
		entry.setImage(new byte[0]);
		entry.setUser(user);

		when(entryRepository.findByUserId(1)).thenReturn(Arrays.asList(entry));

		List<GetJacketArticle> articles = service.getAllArticles(1);

		assertThat(articles.size()).isEqualTo(1);
	}

	@Test
	public void getEntry_should_return_a_single_entry() throws SerialException, SQLException {

		Long id = 1L;
		Article entryIn = new Article();
		User user = new User();
		user.setId(1L);
		entryIn.setUser(user);
		entryIn.setImage(new byte[0]);
		when(entryRepository.findById(id)).thenReturn(Optional.of(entryIn));

		service = new ArticleDetailsServiceOnArticleRepository(entryRepository, usersRepository, log);
		GetJacketArticle entry = service.getArticle(id);

		assertThat(entry).isNotNull();
	}

	@Test(expected = JacketServiceException.class)
	public void getEntry_should_throw_an_exception_when_the_id_is_wrong() {

		long id = 1;
		when(entryRepository.findById(id)).thenReturn(Optional.empty());

		service = new ArticleDetailsServiceOnArticleRepository(entryRepository, usersRepository, log);
		service.getArticle(id);
	}

	@Test
	public void getEntry_should_return_a_single_entry_with_the_correct_title_and_url()
			throws SerialException, SQLException {

		long id = 1;
		String title = "Title";
		String url = "URL";

		Article entry = new Article();
		User user = new User();
		user.setId(1L);
		entry.setUser(user);
		entry.setTitle(title);
		entry.setUrl(url);

		entry.setImage(new byte[0]);

		when(entryRepository.findById(id)).thenReturn(Optional.of(entry));

		service = new ArticleDetailsServiceOnArticleRepository(entryRepository, usersRepository, log);
		GetJacketArticle jacketEntry = service.getArticle(id);

		assertThat(jacketEntry.getTitle()).isEqualTo(title);
		assertThat(jacketEntry.getUrl()).isEqualTo(url);
	}

	@Test(expected = JacketServiceException.class)
	public void addEntry_should_throw_an_exception_when_title_is_null() {
		service.addArticle(new AddJacketArticle(1L, "url", null, 1L, new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB)));
	}

	@Test(expected = JacketServiceException.class)
	public void addEntry_should_throw_an_exception_when_url_is_null() {
		service.addArticle(new AddJacketArticle(1L, null, "title", 1L, new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB)));
	}

	@Test(expected = JacketServiceException.class)
	public void addEntry_should_throw_an_exception_when_image_is_null() {
		service.addArticle(new AddJacketArticle(1L, "url", "title", 1L, null));
	}

	@Test(expected = JacketServiceException.class)
	public void addEntry_should_throw_an_exception_when_entry_is_null() {
		service.addArticle(null);
	}
}
