package com.pluralsight.jacket.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.apache.commons.logging.Log;
import org.junit.Before;
import org.junit.Test;

import com.pluralsight.jacket.data.models.Entry;
import com.pluralsight.jacket.data.models.User;
import com.pluralsight.jacket.entry.repository.ArticleRepository;
import com.pluralsight.jacket.entry.service.ArticleDetailsServiceOnArticleRepository;
import com.pluralsight.jacket.entry.service.JacketServiceException;
import com.pluralsight.jacket.entry.service.models.AddJacketArticle;
import com.pluralsight.jacket.entry.service.models.GetJacketArticle;
import com.pluralsight.jacket.security.repository.UsersRepository;

public class JacketEntryServiceOnRepositoryTest {
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
	public void getAllEntries_should_return_all_entries() throws SerialException, SQLException {

		User user = new User();
		Entry entry = new Entry();
		entry.setImage(new byte[0]);
		entry.setUser(user);

		when(entryRepository.findByUserId(1)).thenReturn(Arrays.asList(entry));

		List<GetJacketArticle> entries = service.getAllEntries(1);

		assertThat(entries.size()).isEqualTo(1);
	}

	@Test
	public void getEntry_should_return_a_single_entry() throws SerialException, SQLException {

		Long id = 1L;
		Entry entryIn = new Entry();
		User user = new User();
		user.setId(1L);
		entryIn.setUser(user);
		entryIn.setImage(new byte[0]);
		when(entryRepository.findOne(id)).thenReturn(entryIn);

		service = new ArticleDetailsServiceOnArticleRepository(entryRepository, usersRepository, log);
		GetJacketArticle entry = service.getEntry(id);

		assertThat(entry).isNotNull();
	}

	@Test(expected = JacketServiceException.class)
	public void getEntry_should_throw_an_exception_when_the_id_is_wrong() {

		long id = 1;
		when(entryRepository.findOne(id)).thenReturn(null);

		service = new ArticleDetailsServiceOnArticleRepository(entryRepository, usersRepository, log);
		service.getEntry(id);
	}

	@Test
	public void getEntry_should_return_a_single_entry_with_the_correct_title_and_url()
			throws SerialException, SQLException {

		long id = 1;
		String title = "Title";
		String url = "URL";

		Entry entry = new Entry();
		User user = new User();
		user.setId(1L);
		entry.setUser(user);
		entry.setTitle(title);
		entry.setUrl(url);

		entry.setImage(new byte[0]);

		when(entryRepository.findOne(id)).thenReturn(entry);

		service = new ArticleDetailsServiceOnArticleRepository(entryRepository, usersRepository, log);
		GetJacketArticle jacketEntry = service.getEntry(id);

		assertThat(jacketEntry.getTitle()).isEqualTo(title);
		assertThat(jacketEntry.getUrl()).isEqualTo(url);
	}

	@Test(expected = JacketServiceException.class)
	public void addEntry_should_throw_an_exception_when_title_is_null() {
		service.addEntry(new AddJacketArticle(1L, "url", null, 1L, new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB)));
	}

	@Test(expected = JacketServiceException.class)
	public void addEntry_should_throw_an_exception_when_url_is_null() {
		service.addEntry(new AddJacketArticle(1L, null, "title", 1L, new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB)));
	}

	@Test(expected = JacketServiceException.class)
	public void addEntry_should_throw_an_exception_when_image_is_null() {
		service.addEntry(new AddJacketArticle(1L, "url", "title", 1L, null));
	}

	@Test(expected = JacketServiceException.class)
	public void addEntry_should_throw_an_exception_when_entry_is_null() {
		service.addEntry(null);
	}
}
