package com.dom.duplex.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public final class CSVReader {

	private static final CsvMapper MAPPER = new CsvMapper();

	private CSVReader() {
		// empty by design
	}

	public static <T> List<T> read(final Class<T> clazz, final InputStream stream) throws IOException {
		final CsvSchema schema = MAPPER.schemaFor(clazz).withHeader().withColumnReordering(true);
		final ObjectReader reader = MAPPER.readerFor(clazz).with(schema);
		return reader.<T>readValues(stream).readAll();
	}
}
