package com.dom.duplex.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dom.duplex.client.ThirdyPartyApiClient;
import com.dom.duplex.repository.CsvCrud;
import com.dom.duplex.repository.domain.CsvEntry;
import com.dom.duplex.utils.CSVReader;

@Service
public class UserService {

	@Autowired
	private CsvCrud crudRepository;

	@Autowired
	private ThirdyPartyApiClient thirdyPartyApiClient;

	@Scheduled(fixedRate = 4000)
	public void consumeThirdPartyUsers() throws IOException {

		final ResponseEntity<String> apiUsers = thirdyPartyApiClient.getUserCsvs();

		final List<CsvEntry> csvEntrys = CSVReader.read(CsvEntry.class,
				new ByteArrayInputStream(apiUsers.getBody().getBytes()));

		crudRepository.save(csvEntrys);
	}
}
