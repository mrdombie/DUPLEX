package com.dom.duplex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dom.duplex.repository.domain.CsvEntry;

@Repository
public interface CsvEntryRepository extends JpaRepository<CsvEntry, Long> {
}
