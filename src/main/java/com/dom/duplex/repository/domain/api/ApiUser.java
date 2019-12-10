package com.dom.duplex.repository.domain.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiUser {

	private String name;
	private int age;
	private int height;

	public String getName() {
		return name;
	}

	public ApiUser setName(final String name) {
		this.name = name;
		return this;
	}

	public int getAge() {
		return age;
	}

	public ApiUser setAge(final int age) {
		this.age = age;
		return this;
	}

	public int getHeight() {
		return height;
	}

	public ApiUser setHeight(final int height) {
		this.height = height;
		return this;
	}

}
