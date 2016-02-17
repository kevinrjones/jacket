package com.pluralsight.jacket.entry.service;

import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.springframework.transaction.annotation.Transactional;

import static com.pluralsight.image.ImageConverter.getByteArrayFromImage;
import static com.pluralsight.image.ImageConverter.getImageFromByteArray;
import com.pluralsight.jacket.data.models.Entry;
import com.pluralsight.jacket.data.models.User;
import com.pluralsight.jacket.entry.repository.ArticleRepository;
import com.pluralsight.jacket.entry.service.models.AddJacketArticle;
import com.pluralsight.jacket.entry.service.models.GetJacketArticle;
import com.pluralsight.jacket.security.repository.UsersRepository;

@Named
@Transactional(readOnly = true)
public class ArticleDetailsServiceOnArticleRepository implements JacketArticleService {

	ArticleRepository entryRepository;
	UsersRepository userRepository;
	Log log;

	@Inject
	public ArticleDetailsServiceOnArticleRepository(ArticleRepository entryRepository, UsersRepository userRepository,	Log log) {
		this.entryRepository = entryRepository;
		this.userRepository = userRepository;
		this.log = log;
	}

	@Override
	public List<GetJacketArticle> getAllEntries(long id) {
		Iterable<Entry> entries = entryRepository.findByUserId(id);
		List<GetJacketArticle> serviceEntries = new LinkedList<GetJacketArticle>();
		if (entries != null) {
			entries.forEach(e -> serviceEntries.add(
					new GetJacketArticle(e.getUser().getId(), e.getUrl(), e.getTitle(), e.getId()/*, getImageFromEntryByteArray(e)*/)));
		} else {
			log.debug("*********** repository return null");
		}
		return serviceEntries;
	}


	@Override
	@Transactional(readOnly = false)
	public void updateEntry(AddJacketArticle jacketEntry) {

	}

	@Override
	@Transactional(readOnly = false)
	public long addEntry(AddJacketArticle jacketEntry) {

		if (jacketEntry == null)
			throw new JacketServiceException("Unable to add an entry for " + jacketEntry);
		if (jacketEntry.getTitle() == null)
			throw new JacketServiceException("Unable to add an entry for " + jacketEntry);
		if (jacketEntry.getUrl() == null)
			throw new JacketServiceException("Unable to add an entry for " + jacketEntry);
		if (jacketEntry.getImage() == null)
			throw new JacketServiceException("Unable to add an entry for " + jacketEntry);

		User user = userRepository.findOne(jacketEntry.getUserId());
		Entry entry = new Entry();

		entry.setUser(user);		
		entry.setTitle(jacketEntry.getTitle());
		entry.setUrl(jacketEntry.getUrl());
		entry.setImage(getByteArrayFromImage(jacketEntry.getImage(), log));

		entryRepository.save(entry);
		return entry.getId();
	}

	@Override
	public GetJacketArticle getEntry(long id) {
		Entry entry = entryRepository.findOne(id);

		if (entry == null)
			throw new JacketServiceException("Unable to find entry in repository for id " + id);
		Long userId = entry.getUser().getId(); 
		String url = entry.getUrl(); 
		String title = entry.getTitle();
		Long entryid = entry.getId();
		
		GetJacketArticle jacketEntry = new GetJacketArticle(userId, url, title, entryid);

		return jacketEntry;
	}
	
	@Override
	public Image getEntryImage(Long entryId) {
		Entry entry = entryRepository.findOne(entryId);

		if (entry == null)
			throw new JacketServiceException("Unable to find entry in repository for id " + entryId);

		byte[] bytes = entry.getImage();
		return getImageFromByteArray(bytes, log);
	}


}
