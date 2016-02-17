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
import com.pluralsight.jacket.entry.service.JacketEntryService;
import com.pluralsight.jacket.entry.service.models.GetJacketEntry;
import com.pluralsight.jacket.security.service.models.AuthenticatedUser;
import com.pluralsight.jacketweb.viewmodels.Entry;
import com.pluralsight.security.CurrentUser;

@Controller
@RequestMapping(value = { "/", "/link" })
public class ArticleController {

	private JacketEntryService service;
	private Log log;

	@Inject
	public ArticleController(JacketEntryService service, Log log) {
		this.service = service;
		this.log = log;
	}

	@RequestMapping(value = { "/", "" })
	public ModelAndView index(@CurrentUser AuthenticatedUser user) {
		List<GetJacketEntry> serviceEntries = service.getAllEntries(user.getId());
		List<Entry> entries = new ArrayList<Entry>();
		serviceEntries.forEach(e -> {
			Entry entry = new Entry(e);
			entries.add(entry);
		});

		ModelAndView mv = new ModelAndView("link/index");
		mv.addObject("entries", entries);
		
		return mv;
	}
	//InputStreamResource
	@RequestMapping(value = "image/{imageId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> downloadLinkInage(@PathVariable Long imageId) {
	    BufferedImage image = (BufferedImage) service.getEntryImage(imageId);

	    byte[] bytes = getByteArrayFromImage(image, log);
	    ByteArrayResource bsr = new ByteArrayResource(bytes);
	    return ResponseEntity.ok()
	            .contentLength(bytes.length)
	            .contentType(MediaType.parseMediaType("image/png"))
	            .body(bsr);
	}
	
}
