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

import com.pluralsight.jacket.entry.repository.EntryRepository;
import com.pluralsight.jacket.entry.service.JacketEntryService;
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
	public void getEntry_should_get_the_entrys_correct_title_and_url_and_image() {

		JacketEntry entry = service.getEntry(1L);

		assertThat(entry.getUrl()).isEqualTo("http://news.bbc.co.uk");
		assertThat(entry.getTitle()).isEqualTo("News");
		assertThat(entry.getImage()).isNotNull();
	}

	@Test
	public void getAllEntries_should_get_all_entries_from_datastore() {

		List<JacketEntry> entry = service.getAllEntries();

		assertThat(entry.size()).isEqualTo(2);
	}

	@Test
	public void addEntry_should_insert_a_valid_entry_into_store() {

		List<JacketEntry> entries = service.getAllEntries();

		assertThat(entries.size()).isEqualTo(2);

		service.addEntry(new JacketEntry("title", "url", createImage()));

		entries = service.getAllEntries();
		assertThat(entries.size()).isEqualTo(3);
	}

	@Test
	public void addEntry_added_entry_should_have_a_valid_title() {

		long id = service.addEntry(new JacketEntry("new url", "new title", createImage()));

		JacketEntry entry = service.getEntry(id);
		assertThat(entry.getTitle()).isEqualTo("new title");
	}

	@Test
	public void addEntry_added_entry_should_have_a_valid_url() {

		long id = service.addEntry(new JacketEntry("new url", "new title", createImage()));

		JacketEntry entry = service.getEntry(id);
		assertThat(entry.getUrl()).isEqualTo("new url");
	}

	private Image createImage() {
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
