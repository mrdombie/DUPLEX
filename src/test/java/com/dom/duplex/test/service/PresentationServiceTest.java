package com.dom.duplex.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;

import com.dom.duplex.client.PresentationApi;
import com.dom.duplex.client.transformer.UserApiTransformer;
import com.dom.duplex.repository.CsvEntryRepository;
import com.dom.duplex.repository.domain.CsvEntry;
import com.dom.duplex.repository.example.ExampleCriteria;
import com.dom.duplex.service.PresentationService;

@SpringBootTest
public class PresentationServiceTest {

	private static final int LEGA_DATA_AGE_DEFAULT = 21;

	private static final String LEGAL_DATA_NAME = "PICARD";

	private static final int LEGAL_DATA_AGE = 18;

	@InjectMocks
	private PresentationService presentationService;

	@Mock
	private final Map<Integer, CsvEntry> toProcessList = new HashMap<>();

	@Mock
	private CsvEntryRepository csvEntryRepository;

	@Mock
	private PresentationApi apiClient;

	@BeforeAll
	private void setup() {
		toProcessList.put(1, new CsvEntry().setName("DOM").setAge(21));
	}

	@Scheduled(fixedRate = 1000)
	public void send() {
		final Optional<Entry<Integer, CsvEntry>> findFirst = toProcessList.entrySet().stream().findFirst();

		if (findFirst.isPresent()) {
			final CsvEntry csvEntry = findFirst.get().getValue();

			checkAgeAndProcess(csvEntry);

			apiClient.sendDataUser(UserApiTransformer.toApiUser(csvEntry));
			toProcessList.remove(csvEntry.getId());
		}
	}

	private void checkAgeAndProcess(final CsvEntry csvEntry) {

		if (csvEntry.getAge() > LEGAL_DATA_AGE) {
			final List<Integer> ages = csvEntryRepository.findAll(ExampleCriteria.byCsvId(csvEntry.getCsvId())).stream()
					.map(CsvEntry::getAge).collect(Collectors.toList());

			processLegalRequirements(csvEntry, ages);
		}
	}

	private void processLegalRequirements(final CsvEntry csvEntry, final List<Integer> ages) {
		final int averageAge = average(ages);

		if (averageAge > 18) {
			csvEntry.setAge(averageAge);
		} else {
			csvEntry.setAge(LEGA_DATA_AGE_DEFAULT);
		}

		csvEntry.setName(LEGAL_DATA_NAME);
	}

	private Integer average(final List<Integer> ages) {

		Integer base = 0;

		for (final Integer integer : ages) {
			base += integer;
		}

		return base / ages.size();
	}
}
