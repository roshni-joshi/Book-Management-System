package com.bookmanagement.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookmanagement.model.Book;
import com.bookmanagement.service.BookService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/basicauth")
	public boolean authenticateUser()
	{
		return true;
	}
	
	@GetMapping("/users/{username}/books")
	public List<Book> getAllBooks(@PathVariable String username) {
		return bookService.getAllBooks(username);
	}
	
	@GetMapping("/users/{username}/books/{id}")
	public Book getBook(@PathVariable String username, @PathVariable Long id) {
		return bookService.getBook(id);
	}
	
	@DeleteMapping("/users/{username}/books/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable String username, @PathVariable Long id) {
		bookService.deleteBook(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/users/{username}/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable String username, @PathVariable Long id, @RequestBody Book book) {
		if(book != null)
		{
			book.setId(id);
			book.setUsername(username);
		}
		
		Book updatedBook = bookService.saveBook(book);
		
		return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
	}
	
	@PostMapping("/users/{username}/books")
	public ResponseEntity<Void> createBook(@PathVariable String username, @RequestBody Book book) {
		if(book != null)
		{
			book.setUsername(username);
		}
		
		Book savedBook = bookService.saveBook(book);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBook.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
