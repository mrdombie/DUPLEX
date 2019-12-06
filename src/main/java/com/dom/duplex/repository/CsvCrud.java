package com.dom.duplex.repository;

import java.util.List;
import java.util.Optional;

import com.dom.duplex.repository.domain.CsvEntry;

public interface CsvCrud {
	void save(List<CsvEntry> list);

	Optional<CsvEntry> readPendingCsvRecord();
}
