package com.dom.duplex.service;

import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dom.duplex.client.PresentationApi;
import com.dom.duplex.client.transformer.UserApiTransformer;
import com.dom.duplex.component.CsvPoolComponent;
import com.dom.duplex.repository.CsvEntryRepository;
import com.dom.duplex.repository.domain.CsvEntry;
import com.dom.duplex.repository.domain.RequestStatus;
import com.dom.duplex.repository.example.ExampleCriteria;
import com.google.common.util.concurrent.RateLimiter;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PresentationService {

	private static final int LEGA_DATA_AGE_DEFAULT = 21;
	private static final String LEGAL_DATA_NAME = "PICARD";
	private static final int LEGAL_DATA_AGE = 18;

	@Autowired
	private CsvPoolComponent csvPoolComponent;

	@Autowired
	private CsvEntryRepository csvEntryRepository;

	@Autowired
	private PresentationApi apiClient;

	@Autowired
	private RateLimiter rateLimiter;

	@Scheduled(fixedRate = 1000)
	@Transactional
	public void send() {

		rateLimiter.acquire();

		final Optional<Entry<Integer, CsvEntry>> findFirst = csvPoolComponent.getProcessableEntrys().entrySet().stream()
				.findFirst();

		if (findFirst.isPresent()) {
			final CsvEntry csvEntry = findFirst.get().getValue();

			checkAgeAndProcess(csvEntry);

			apiClient.sendDataUser(UserApiTransformer.toApiUser(csvEntry));

			csvEntryRepository.save(csvEntry.setRequestStatus(RequestStatus.PROCESSED));

			csvPoolComponent.remove(csvEntry.getId());
		}
	}

	private void checkAgeAndProcess(final CsvEntry csvEntry) {

		if (csvEntry.getAge() < LEGAL_DATA_AGE) {
			final List<Integer> ages = csvEntryRepository.findAll(ExampleCriteria.byCsvId(csvEntry.getCsvId())).stream()
					.map(CsvEntry::getAge).collect(Collectors.toList());

			processLegalRequirements(csvEntry, ages);
		}
	}

	private void processLegalRequirements(final CsvEntry csvEntry, final List<Integer> ages) {
		final int averageAge = average(ages);

		if (averageAge < LEGAL_DATA_AGE) {
			csvEntry.setAge(averageAge);
		} else {
			csvEntry.setAge(LEGA_DATA_AGE_DEFAULT);
		}

		csvEntry.setName(LEGAL_DATA_NAME);
	}

	private Integer average(final List<Integer> ages) {

		int base = 0;

		for (final Integer age : ages) {
			base += age;
		}

		return base / ages.size();
	}
}
