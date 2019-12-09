package com.dom.duplex.endpoints;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dom.duplex.repository.CsvCrud;
import com.dom.duplex.repository.domain.CsvEntry;
import com.dom.duplex.utils.CSVReader;

@RestController
public class CsvController {

	@Autowired
	private CsvCrud csvCrud;

	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public void uploadMultipart(@RequestParam("file") final MultipartFile file) throws IOException {
		csvCrud.save(CSVReader.read(CsvEntry.class, file.getInputStream()));
	}
}
