package com.pluralsight.jacketweb.controller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static com.pluralsight.image.ImageConverter.getByteArrayFromImage;

import com.pluralsight.jacket.article.service.JacketArticleService;
import com.pluralsight.jacket.article.service.models.GetJacketArticle;
import com.pluralsight.jacket.security.service.models.AuthenticatedUser;
import com.pluralsight.jacketweb.viewmodels.Article;
import com.pluralsight.security.CurrentUser;

@Controller
@RequestMapping(value = { "/", "/article" })
public class ArticleController {

	private JacketArticleService service;
	private Log log;

	@Inject
	public ArticleController(JacketArticleService service, Log log) {
		this.service = service;
		this.log = log;
	}

	@RequestMapping(value = { "/", "" })
	public ModelAndView index(@CurrentUser AuthenticatedUser user) {
		List<GetJacketArticle> serviceEntries = service.getAllArticles(user.getId());
		List<Article> articles = new ArrayList<Article>();
		serviceEntries.forEach(e -> {
			Article entry = new Article(e);
			articles.add(entry);
		});

		ModelAndView mv = new ModelAndView("article/index");
		mv.addObject("articles", articles);
		
		return mv;
	}
	//InputStreamResource
	@RequestMapping(value = "image/{imageId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> downloadLinkInage(@PathVariable Long imageId) {
	    BufferedImage image = (BufferedImage) service.getArticleImage(imageId);

	    byte[] bytes = getByteArrayFromImage(image, log);
	    ByteArrayResource bsr = new ByteArrayResource(bytes);
	    return ResponseEntity.ok()
	            .contentLength(bytes.length)
	            .contentType(MediaType.parseMediaType("image/png"))
	            .body(bsr);
	}
	
}
