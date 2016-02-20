package com.pluralsight.jacket.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pluralsight.jacket.article.repository.ArticleRepository;
import com.pluralsight.jacket.article.service.JacketArticleService;
import com.pluralsight.jacket.article.service.models.AddJacketArticle;
import com.pluralsight.jacket.article.service.models.GetJacketArticle;
import com.pluralsight.repository.AbstractTest;

public class ArticleServiceTest extends AbstractTest {

	@Autowired
	ArticleRepository repository;
	@Autowired
	JacketArticleService service;

	@Test
	public void shouldFindArticleById() {

		GetJacketArticle Article = service.getArticle(1L);

		assertThat(Article).isNotNull();
	}

	@Test
	public void getArticle_should_get_the_Articles_correct_title_and_url_and_image() {

		GetJacketArticle Article = service.getArticle(1L);

		assertThat(Article.getUrl()).isEqualTo("http://news.bbc.co.uk");
		assertThat(Article.getTitle()).isEqualTo("News");
		//assertThat(Article.getImage()).isNotNull();
	}

	@Test
	public void getAllEntries_should_get_all_articles_from_datastore() {

		List<GetJacketArticle> Article = service.getAllArticles(1);

		assertThat(Article.size()).isEqualTo(2);
	}

	@Test
	public void addArticle_should_insert_a_valid_Article_into_store() {

		List<GetJacketArticle> articles = service.getAllArticles(1);

		assertThat(articles.size()).isEqualTo(2);

		service.addArticle(new AddJacketArticle(1L, "title", "url", 1L, createImage()));

		articles = service.getAllArticles(1);
		assertThat(articles.size()).isEqualTo(3);
	}

	@Test
	public void addArticle_added_Article_should_have_a_valid_title() {

		long id = service.addArticle(new AddJacketArticle(1L, "new url", "new title", 1L, createImage()));

		GetJacketArticle Article = service.getArticle(id);
		assertThat(Article.getTitle()).isEqualTo("new title");
	}

	@Test
	public void addArticle_added_Article_should_have_a_valid_url() {

		long id = service.addArticle(new AddJacketArticle(1L, "new url", "new title", 1L, createImage()));

		GetJacketArticle Article = service.getArticle(id);
		assertThat(Article.getUrl()).isEqualTo("new url");
	}

	private BufferedImage createImage() {
		int w = 100;
		int h = 100;
		int pix[] = new int[w * h];
		int index = 0;
		for (int y = 0; y < h; y++) {
			int red = (y * 255) / (h - 1);
			for (int x = 0; x < w; x++) {
				int blue = (x * 255) / (w - 1);
				pix[index++] = (255 << 24) | (red << 16) | blue;
			}
		}
		Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(w, h, pix, 0, w));
		BufferedImage bufferedImage = getRenderedImage(image);
		return bufferedImage;
	}

	private BufferedImage getRenderedImage(Image in) {
		int w = in.getWidth(null);
		int h = in.getHeight(null);
		int type = BufferedImage.TYPE_INT_RGB;
		BufferedImage out = new BufferedImage(w, h, type);
		Graphics2D g2 = out.createGraphics();
		g2.drawImage(in, 0, 0, null);
		g2.dispose();
		return out;
	}

}
