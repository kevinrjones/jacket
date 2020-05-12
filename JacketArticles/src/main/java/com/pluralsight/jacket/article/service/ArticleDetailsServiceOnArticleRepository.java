package com.pluralsight.jacket.article.service;

import static com.pluralsight.image.ImageConverter.getByteArrayFromImage;
import static com.pluralsight.image.ImageConverter.getImageFromByteArray;

import java.awt.Image;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pluralsight.jacket.article.repository.ArticleRepository;
import com.pluralsight.jacket.article.service.models.AddJacketArticle;
import com.pluralsight.jacket.article.service.models.GetJacketArticle;
import com.pluralsight.jacket.data.models.Article;
import com.pluralsight.jacket.data.models.User;
import com.pluralsight.jacket.security.repository.UsersRepository;

@Service
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
	public List<GetJacketArticle> getAllArticles(long id) {
		Iterable<Article> articles = entryRepository.findByUserId(id);
		List<GetJacketArticle> serviceEntries = new LinkedList<GetJacketArticle>();
		if (articles != null) {
			articles.forEach(e -> serviceEntries.add(
					new GetJacketArticle(e.getUser().getId(), e.getUrl(), e.getTitle(), e.getId()/*, getImageFromEntryByteArray(e)*/)));
		} else {
			log.debug("*********** repository return null");
		}
		return serviceEntries;
	}


	@Override
	@Transactional(readOnly = false)
	public void updateArticle(AddJacketArticle jacketEntry) {

	}

	@Override
	@Transactional(readOnly = false)
	public long addArticle(AddJacketArticle jacketEntry) {

		if (jacketEntry == null)
			throw new JacketServiceException("Unable to add an entry for " + jacketEntry);
		if (jacketEntry.getTitle() == null)
			throw new JacketServiceException("Unable to add an entry for " + jacketEntry);
		if (jacketEntry.getUrl() == null)
			throw new JacketServiceException("Unable to add an entry for " + jacketEntry);
		if (jacketEntry.getImage() == null)
			throw new JacketServiceException("Unable to add an entry for " + jacketEntry);

		var user = userRepository.findById(jacketEntry.getUserId());
		Article entry = new Article();

		entry.setUser(user.get());
		entry.setTitle(jacketEntry.getTitle());
		entry.setUrl(jacketEntry.getUrl());
		entry.setImage(getByteArrayFromImage(jacketEntry.getImage(), log));

		entryRepository.save(entry);
		return entry.getId();
	}

	@Override
	public GetJacketArticle getArticle(long id) {
		Optional<Article> optionalArticle = entryRepository.findById(id);

		if (optionalArticle.isEmpty())
			throw new JacketServiceException("Unable to find entry in repository for id " + id);

		var article = optionalArticle.get();
		Long userId = article.getUser().getId();
		String url = article.getUrl();
		String title = article.getTitle();
		Long entryid = article.getId();
		
		GetJacketArticle jacketEntry = new GetJacketArticle(userId, url, title, entryid);

		return jacketEntry;
	}
	
	@Override
	public Image getArticleImage(Long entryId) {
		var optionalArticle = entryRepository.findById(entryId);


		if (optionalArticle.isEmpty())
			throw new JacketServiceException("Unable to find entry in repository for id " + entryId);

		var entry = optionalArticle.get();
		byte[] bytes = entry.getImage();
		return getImageFromByteArray(bytes, log);
	}


}
