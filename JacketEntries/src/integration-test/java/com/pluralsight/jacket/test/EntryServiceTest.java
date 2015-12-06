package com.pluralsight.jacket.test;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pluralsight.jacket.entry.data.models.Entry;
import com.pluralsight.jacket.entry.repository.EntryRepository;
import com.pluralsight.jacket.entry.service.JacketEntryService;
import com.pluralsight.jacket.entry.service.JacketServiceException;
import com.pluralsight.jacket.entry.service.models.JacketEntry;
import com.pluralsight.repository.AbstractTest;

public class EntryServiceTest extends AbstractTest {

	@Autowired
	EntryRepository repository;
	@Autowired
	JacketEntryService service;

	@Test
	public void shouldFindEntryById() {

		JacketEntry entry = service.getEntry(1L);

		assertThat(entry).isNotNull();
	}

	@Test
	public void getEntry_should_get_the_entrys_correct_title_and_url() {

		JacketEntry entry = service.getEntry(1L);

		assertThat(entry.getUrl()).isEqualTo("http://news.bbc.co.uk");
		assertThat(entry.getTitle()).isEqualTo("News");
	}

	@Test
	public void getAllEntries_should_get_all_entries() {

		List<JacketEntry> entry = service.getAllEntries();

		assertThat(entry.size()).isEqualTo(2);
	}

	@Test
	public void addEntry_should_insert_a_valid_entry() {

		List<JacketEntry> entries = service.getAllEntries();

		assertThat(entries.size()).isEqualTo(2);

		service.addEntry(new JacketEntry("title", "url"));

		entries = service.getAllEntries();
		assertThat(entries.size()).isEqualTo(3);
	}

	@Test
	public void addEntry_added_entry_should_have_a_valid_title() {

		long id = service.addEntry(new JacketEntry("new url", "new title"));

		JacketEntry entry = service.getEntry(id);
		assertThat(entry.getTitle()).isEqualTo("new title");
	}

	@Test
	public void addEntry_added_entry_should_have_a_valid_url() {

		long id = service.addEntry(new JacketEntry("new url", "new title"));

		JacketEntry entry = service.getEntry(id);
		assertThat(entry.getUrl()).isEqualTo("new url");
	}

}
