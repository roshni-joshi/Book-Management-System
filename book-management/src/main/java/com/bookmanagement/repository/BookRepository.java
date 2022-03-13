package com.bookmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmanagement.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	public List<Book> findByUsername(String username);
}
