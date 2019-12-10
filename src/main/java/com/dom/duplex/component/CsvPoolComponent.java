package com.dom.duplex.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dom.duplex.repository.CsvEntryRepository;
import com.dom.duplex.repository.domain.CsvEntry;
import com.dom.duplex.repository.domain.RequestStatus;

@Component
@Scope
public class CsvPoolComponent {

	@Autowired
	private CsvEntryRepository csvEntryRepository;

	private final Map<Integer, CsvEntry> processableEntrys = new HashMap<>();

	/**
	 * Retrieve the list of current processable requests inside the DB.
	 *
	 * @return a Map container the key value pairs of the CSV component
	 */

	public Map<Integer, CsvEntry> getProcessableEntrys() {

		// TODO Find out why this JPA querie just doesnt want to work - filter for now
		final List<CsvEntry> entrys = csvEntryRepository.findAll().stream()
				.filter(csv -> csv.getRequestStatus().equals(RequestStatus.HOLDING)).collect(Collectors.toList());

		for (final CsvEntry csvEntry : entrys) {
			processableEntrys.putIfAbsent(csvEntry.getId(), csvEntry);
		}

		return processableEntrys;
	}

	public Map<Integer, CsvEntry> getEntrys() {
		return processableEntrys;
	}

	public void remove(final int csvId) {
		processableEntrys.remove(csvId);
	}
}
