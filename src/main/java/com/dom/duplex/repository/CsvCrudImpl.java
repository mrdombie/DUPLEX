package com.dom.duplex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dom.duplex.repository.domain.Csv;
import com.dom.duplex.repository.domain.CsvEntry;
import com.dom.duplex.repository.example.ExampleCriteria;

@Repository
public class CsvCrudImpl implements CsvCrud {

	@Autowired
	private CsvEntryRepository csvEntryRepository;

	@Autowired
	private CsvRepository csvRepository;

	@Override
	@Transactional
	public void save(final List<CsvEntry> csvs) {
		final int parentId = csvRepository.save(new Csv()).getId(); // TODO Setup Date etc'

		updateId(csvs, parentId);

		csvEntryRepository.saveAll(csvs);
	}

	@Override
	public Optional<CsvEntry> readPendingCsvRecord() {
		return csvEntryRepository.findOne(ExampleCriteria.pending());
	}

	private void updateId(final List<CsvEntry> csvs, final int storedCsv) {
		csvs.forEach(ce -> {
			ce.setCsvId(storedCsv);
		});
	}
}
