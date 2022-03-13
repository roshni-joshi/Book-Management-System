package com.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmanagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(final String username);
}
