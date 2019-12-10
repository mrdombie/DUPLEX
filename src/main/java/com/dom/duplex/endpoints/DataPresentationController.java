package com.dom.duplex.endpoints;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dom.duplex.service.PresentationService;

@RestController
public class DataPresentationController {

	@Autowired
	private PresentationService presentationService;

	@PostMapping(path = "/presentation")
	public void sendPresentation() throws IOException {
		presentationService.send();
	}
}
