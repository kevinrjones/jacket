package com.pluralsight.jacket.test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.junit.Before;
import org.junit.Test;

import com.pluralsight.jacket.entry.data.models.Entry;
import com.pluralsight.jacket.entry.repository.EntryRepository;
import com.pluralsight.jacket.entry.service.EntryRepositoryEntryDetailsService;
import com.pluralsight.jacket.entry.service.JacketServiceException;
import com.pluralsight.jacket.entry.service.models.JacketEntry;

public class JacketEntryServiceOnRepositoryTest {
    EntryRepositoryEntryDetailsService jacketEntryServiceOnRepository;
    EntryRepository repository;
    Log log;
    EntryRepositoryEntryDetailsService service;
    
    @Before
    public void before(){
        repository = mock(EntryRepository.class);
        log = mock(Log.class);
        service = new EntryRepositoryEntryDetailsService(repository, log);
    }

    @Test
    public void getAllEntries_should_return_all_entries() {    

        when(repository.findAll()).thenReturn(Arrays.asList(new Entry()));
        
        List<JacketEntry> entries = service.getAllEntries();

        assertThat(entries.size()).isEqualTo(1);
    }

    @Test
    public void getEntry_should_return_q_single_entry() {    

    	long id = 1;
        when(repository.findOne(id)).thenReturn(new Entry());
        
        service = new EntryRepositoryEntryDetailsService(repository, log);
        JacketEntry entry = service.getEntry(id);

        assertThat(entry).isNotNull();
    }
    
    @Test(expected= JacketServiceException.class)
    public void getEntry_should_throw_an_exception_when_the_id_is_wrong() {    

    	long id = 1;
        when(repository.findOne(id)).thenReturn(null);
        
        service = new EntryRepositoryEntryDetailsService(repository, log);
        JacketEntry entry = service.getEntry(id);

    }
    
    @Test
    public void getEntry_should_return_a_single_entry_with_the_correct_title_and_url() {    

    	long id = 1;
    	String title = "Title";
    	String url = "URL";

    	Entry entry = new Entry();
    	entry.setTitle(title);
    	entry.setUrl(url);
    	
    	when(repository.findOne(id)).thenReturn(entry);
        
        service = new EntryRepositoryEntryDetailsService(repository, log);
        JacketEntry jacketEntry = service.getEntry(id);

        assertThat(jacketEntry.getTitle()).isEqualTo(title);
        assertThat(jacketEntry.getUrl()).isEqualTo(url);
    }

	@Test(expected= JacketServiceException.class)
	public void addEntry_should_throw_an_exception_when_title_is_null() {
		service.addEntry(new JacketEntry("url", null));
	}

	@Test(expected= JacketServiceException.class)
	public void addEntry_should_throw_an_exception_when_url_is_null() {
		service.addEntry(new JacketEntry(null, "title"));
	}

	@Test(expected= JacketServiceException.class)
	public void addEntry_should_throw_an_exception_when_entry_is_null() {
		service.addEntry(null);
	}

}
