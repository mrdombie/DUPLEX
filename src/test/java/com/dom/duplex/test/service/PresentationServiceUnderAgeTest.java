package com.dom.duplex.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.unitils.reflectionassert.ReflectionAssert;

import com.dom.duplex.client.PresentationApi;
import com.dom.duplex.component.CsvPoolComponent;
import com.dom.duplex.repository.CsvEntryRepository;
import com.dom.duplex.repository.domain.CsvEntry;
import com.dom.duplex.repository.domain.api.DataApiUser;
import com.dom.duplex.service.PresentationService;
import com.google.common.util.concurrent.RateLimiter;

@SpringBootTest
public class PresentationServiceUnderAgeTest {

	private static final String NAME = "Dom";

	private static final int HEIGHT = 90;

	private static final int AGE = 17;

	private static final Integer DB_ID = 1;

	private static final String UNDER_AGE_NAME = "PICARD";

	private static final Map<Integer, CsvEntry> PROCESSABLE_CSVS = new HashMap<>();

	private static final List<CsvEntry> ALL_CSV_RECORDS = new ArrayList<>();

	@InjectMocks
	private PresentationService presentationService;

	@Mock
	private CsvPoolComponent csvPoolComponent;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private CsvEntryRepository csvEntryRepository;

	@Mock
	private PresentationApi apiClient;

	@Mock
	private RateLimiter rateLimiter;

	@Captor
	private ArgumentCaptor<DataApiUser> captor;

	@SuppressWarnings("unchecked")
	@BeforeEach
	public void setup() {

		PROCESSABLE_CSVS.put(DB_ID, new CsvEntry().setAge(AGE).setHeight(HEIGHT).setName(NAME));
		ALL_CSV_RECORDS.add(new CsvEntry().setAge(17).setName(NAME).setHeight(HEIGHT));

		Mockito.when(csvPoolComponent.getEntrys()).thenReturn(PROCESSABLE_CSVS);
		Mockito.when(csvEntryRepository.findAll(Mockito.any(Example.class))).thenReturn(ALL_CSV_RECORDS);
		Mockito.when(rateLimiter.getRate()).thenReturn(1.0);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testUserUnderEightTeen() {
		final DataApiUser apiUser = new DataApiUser().setAge(AGE).setName(UNDER_AGE_NAME).setHeight(HEIGHT);

		presentationService.send();

		Mockito.verify(rateLimiter).acquire();
		Mockito.verify(apiClient).sendDataUser(captor.capture());

		ReflectionAssert.assertLenientEquals("Unexpected data api user found", captor.getValue(), apiUser);
	}
}