package com.bookmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmanagement.model.Book;
import com.bookmanagement.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks(String username) {
		List<Book> books = new ArrayList<>();
		
		if(username != null)
		{
			books = bookRepository.findByUsername(username);
		}
		return books;
	}
	
	public Book getBook(Long id) {
		Book book = null;
		
		if(id != null)
		{
			book = bookRepository.findById(id).get();
		}
		return book;
	}
	
	public Book saveBook(Book book)
	{
		Book savedBook = null;
		if(book != null)
		{
			savedBook = bookRepository.save(book);
		}
		return savedBook;
	}
	
	public void deleteBook(Long id) {
		if(id != null)
		{
			bookRepository.deleteById(id);
		}
	}

}
