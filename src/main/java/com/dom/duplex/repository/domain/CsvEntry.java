package com.dom.duplex.repository.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(250) NOT NULL, age INT NOT
 * NULL, height INT NOT NULL, status_id INT NOT NULL
 */

@Entity
@Table(name = "csv_entry")
public class CsvEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	// TODO Add Foreign Key here
	@Column(name = "csv_id")
	private int csvId;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;

	@Column(name = "height")
	private int height;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private RequestStatus requestStatus;

	public int getId() {
		return id;
	}

	public CsvEntry setId(final int id) {
		this.id = id;
		return this;
	}

	public int getCsvId() {
		return csvId;
	}

	public CsvEntry setCsvId(final int csvId) {
		this.csvId = csvId;
		return this;
	}

	public String getName() {
		return name;
	}

	public CsvEntry setName(final String name) {
		this.name = name;
		return this;
	}

	public int getAge() {
		return age;
	}

	public CsvEntry setAge(final int age) {
		this.age = age;
		return this;
	}

	public int getHeight() {
		return height;
	}

	public CsvEntry setHeight(final int height) {
		this.height = height;
		return this;
	}

	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public CsvEntry setRequestStatus(final RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
		return this;
	}
}
