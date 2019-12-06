package com.dom.duplex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dom.duplex.repository.domain.Csv;

public interface CsvRepository extends JpaRepository<Csv, Long> {
}
