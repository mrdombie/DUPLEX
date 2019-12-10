package com.dom.duplex.repository.domain;

public class Report {

	private int rows;
	private int csvs;

	public Report(final int rows, final int csvs) {
		this.rows = rows;
		this.csvs = csvs;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(final int rows) {
		this.rows = rows;
	}

	public int getCsvs() {
		return csvs;
	}

	public void setCsvs(final int csvs) {
		this.csvs = csvs;
	}

}
