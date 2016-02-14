package com.pluralsight.jacket.entry.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
		if (entries != null) {
			entries.forEach(
					e -> serviceEntries.add(new JacketEntry(e.getUrl(), e.getTitle(), getImageFromEntryByteArray(e))));
		} else {
			log.debug("*********** repository return null");
		}
		return serviceEntries;
	}

	private Image getImageFromEntryByteArray(Entry entry) {

		if (entry == null)
			throw new IllegalArgumentException();

		Image image = null;

		try {
			byte[] imageBytes = entry.getImage();
			InputStream in = new ByteArrayInputStream(imageBytes);
			image = ImageIO.read(in);
		} catch (IOException e) {
			log.error("Unable to read Blob", e);
			e.printStackTrace();
		}
		return image;
	}

	private byte[] getByteArrayFromImage(Image image)  {

		if (image == null)
			throw new IllegalArgumentException();

		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte[] imageData = null;
		
		try {
			ImageIO.write(bufferedImage, "png", baos);
			imageData = baos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageData;
	}

	@Override
	@Transactional(readOnly = false)
	public void updateEntry(JacketEntry jacketEntry) {

	}

	@Override
	@Transactional(readOnly = false)
	public long addEntry(JacketEntry jacketEntry) {

		if (jacketEntry == null)
			throw new JacketServiceException("Unable to add an entry for " + jacketEntry);
		if (jacketEntry.getTitle() == null)
			throw new JacketServiceException("Unable to add an entry for " + jacketEntry);
		if (jacketEntry.getUrl() == null)
			throw new JacketServiceException("Unable to add an entry for " + jacketEntry);
		if (jacketEntry.getImage() == null)
			throw new JacketServiceException("Unable to add an entry for " + jacketEntry);

		Entry entry = new Entry();
		entry.setTitle(jacketEntry.getTitle());
		entry.setUrl(jacketEntry.getUrl());
		entry.setImage(getByteArrayFromImage(jacketEntry.getImage()));

		repository.save(entry);
		return entry.getId();
	}

	@Override
	public JacketEntry getEntry(long id) {
		Entry entry = repository.findOne(id);

		if (entry == null)
			throw new JacketServiceException("Unable to find entry in repository for id " + id);

		JacketEntry jacketEntry = new JacketEntry(entry.getUrl(), entry.getTitle(), getImageFromEntryByteArray(entry));

		return jacketEntry;
	}
}
