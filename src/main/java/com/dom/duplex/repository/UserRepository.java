package com.dom.duplex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dom.duplex.repository.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
