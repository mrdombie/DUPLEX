package com.dom.duplex.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dom.duplex.repository.CsvEntryRepository;
import com.dom.duplex.repository.domain.CsvEntry;
import com.dom.duplex.repository.example.ExampleCriteria;

@Component
public class CsvBean {

	@Autowired
	private CsvEntryRepository csvEntryRepository;

	private final Map<Integer, CsvEntry> processableEntrys = new HashMap<>();

	@Bean(name = "toProcessList")
	@Scheduled(fixedDelay = 10000)
	public Map<Integer, CsvEntry> getProcessableEntrys() {

		final List<CsvEntry> entrys = csvEntryRepository.findAll(ExampleCriteria.pending());

		for (final CsvEntry csvEntry : entrys) {
			processableEntrys.putIfAbsent(csvEntry.getId(), csvEntry);
		}

		return processableEntrys;
	}
}
