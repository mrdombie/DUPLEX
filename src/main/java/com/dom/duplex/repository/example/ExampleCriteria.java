package com.dom.duplex.repository.example;

import org.springframework.data.domain.Example;

import com.dom.duplex.repository.domain.CsvEntry;
import com.dom.duplex.repository.domain.RequestStatus;

public final class ExampleCriteria {

	private ExampleCriteria() {
		// Empty by design;
	}

	public static Example<CsvEntry> pending() {
		final Example<CsvEntry> csvEntryExample = Example.of(new CsvEntry().setRequestStatus(RequestStatus.PENDING));
		return csvEntryExample;
	}

	public static Example<CsvEntry> byCsvId(final int csvId) {
		final Example<CsvEntry> csvEntryExample = Example.of(new CsvEntry().setCsvId(csvId));
		return csvEntryExample;
	}

}
