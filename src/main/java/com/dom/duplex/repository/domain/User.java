package com.dom.duplex.repository.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(250) NOT NULL, age INT NOT
 * NULL, height INT NOT NULL, status_id INT NOT NULL
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "height")
    private int height;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    public Long getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public User setName(final String name) {
	this.name = name;
	return this;
    }

    public int getAge() {
	return age;
    }

    public User setAge(final int age) {
	this.age = age;
	return this;
    }

    public int getHeight() {
	return height;
    }

    public User setHeight(final int height) {
	this.height = height;
	return this;
    }

    public RequestStatus getRequestStatus() {
	return requestStatus;
    }

    public User setRequestStatus(final RequestStatus requestStatus) {
	this.requestStatus = requestStatus;
	return this;
    }

}
