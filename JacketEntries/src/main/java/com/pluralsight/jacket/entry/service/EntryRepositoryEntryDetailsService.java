package com.pluralsight.jacket.entry.service;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.springframework.transaction.annotation.Transactional;

import com.pluralsight.jacket.entry.data.models.Entry;
import com.pluralsight.jacket.entry.repository.EntryRepository;
import com.pluralsight.jacket.entry.service.models.JacketEntry;

@Named
@Transactional(readOnly = true)
public class EntryRepositoryEntryDetailsService implements JacketEntryService {

	EntryRepository repository;
	Log log;
	
    @Inject
    public EntryRepositoryEntryDetailsService(EntryRepository repository, Log log) {
    	this.repository = repository;
    	this.log = log;
    }


    @Override
    public List<JacketEntry> getAllEntries() {
    	Iterable<Entry> entries = repository.findAll();
    	List<JacketEntry> serviceEntries = new LinkedList<JacketEntry>(); 
    	if(entries != null)
    	{
    		entries.forEach(e -> serviceEntries.add(new JacketEntry(e.getUrl(), e.getTitle(), getImageFromEntryBlob(e))));
    	}
    	else
    	{
    		log.debug("*********** repository return null");
    	}
    	return serviceEntries;
    }


	private Image getImageFromEntryBlob(Entry entry) {
		Blob blob = entry.getImage();
		Image image = null;
		try {
			byte[] imageBytes = blob.getBytes(1, (int)blob.length());
			InputStream in = new ByteArrayInputStream(imageBytes);
			image = ImageIO.read(in);
		} catch (SQLException e) {
			log.error("Unable to read Blob", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Unable to read Blob", e);
			e.printStackTrace();
		}
		return image;
	}


	@Override
	@Transactional(readOnly = false)
	public void updateEntry(JacketEntry jacketEntry) {
		
	}

	@Override
	@Transactional(readOnly = false)
	public long addEntry(JacketEntry jacketEntry) {
		
		if(jacketEntry == null) throw new JacketServiceException("Unable to add an entry for " + jacketEntry);
		if(jacketEntry.getTitle() == null) throw new JacketServiceException("Unable to add an entry for " + jacketEntry);
		if(jacketEntry.getUrl() == null) throw new JacketServiceException("Unable to add an entry for " + jacketEntry);

		Entry entry = new Entry();
		entry.setTitle(jacketEntry.getTitle());
		entry.setUrl(jacketEntry.getUrl());
		
		repository.save(entry);
		return entry.getId();
	}


	@Override
	public JacketEntry getEntry(long id) {
		Entry entry = repository.findOne(id);
		
		if(entry == null) throw new JacketServiceException("Unable to find entry in repository for id " + id);
		
		JacketEntry jacketEntry = new JacketEntry(entry.getUrl(), entry.getTitle(), getImageFromEntryBlob(entry));		
		
		return jacketEntry;
	}
}

