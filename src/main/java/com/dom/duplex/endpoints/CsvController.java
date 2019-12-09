package com.dom.duplex.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.dom.duplex.repository.CsvCrud;
import com.dom.duplex.repository.domain.CsvEntry;
import com.dom.duplex.utils.CSVReader;

@RestController
public class CsvController {

	@Autowired
	private CsvCrud csvCrud;

	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public void uploadMultipart(@RequestParam("file") final MultipartFile file) {
		try {
			csvCrud.save(CSVReader.read(CsvEntry.class, file.getInputStream()));
		} catch (final Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST); // Just so I can write an exception IT .. not
																		// actually implementation
		}
	}
}
