package com.dom.duplex.endpoints;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dom.duplex.repository.CsvCrud;
import com.dom.duplex.repository.domain.CsvEntry;
import com.dom.duplex.utils.CSVReader;

@RestController
public class CsvController {

	@Autowired
	private CsvCrud csvCrud;

	@PostMapping(value = "/upload", consumes = "test/csv")
	public void uploadCsv(@RequestBody final InputStream body) throws IOException {
		csvCrud.save(CSVReader.read(CsvEntry.class, body));
	}

}
