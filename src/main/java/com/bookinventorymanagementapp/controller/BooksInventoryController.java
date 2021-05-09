package com.bookinventorymanagementapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookinventorymanagementapp.BookHelper;
import com.bookinventorymanagementapp.model.Book;
import com.bookinventorymanagementapp.repository.BooksInventoryRepository;
import com.bookinventorymanagementapp.service.GoogleSearchService;

@Controller
@RequestMapping("/inventoryService")
public class BooksInventoryController {

	@Autowired
	private BooksInventoryRepository booksInventoryRepository;

	@Autowired
	private GoogleSearchService googleSearchService;

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String name) {
		try {
			List<Book> books = new ArrayList<Book>();

			if (name == null)
				booksInventoryRepository.findAll().forEach(books::add);
			else {
				Book bookData = booksInventoryRepository.findByNameContaining(name);
				books.add(bookData);
			}

			if (books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/books/{name}")
	public ResponseEntity<Book> getBookById(@PathVariable("name") String name) {
		Book bookData = booksInventoryRepository.findByNameContaining(name);

		if (bookData != null) {
			return new ResponseEntity<>(bookData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		try {
			Book _book = booksInventoryRepository.save(new Book(book.getId(), book.getName(), book.getQuantity()));
			return new ResponseEntity<>(_book, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBookInInventory(@PathVariable("id") String id, @RequestBody Book book) {
		Book bookData = booksInventoryRepository.findOne(id);

		if (bookData != null) {
			Book _book = bookData;
			_book.setId(book.getId());
			_book.setName(book.getName());
			_book.setQuantity(book.getQuantity());
			return new ResponseEntity<>(booksInventoryRepository.save(_book), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<HttpStatus> deleteBookFromInventory(@PathVariable("id") String id) {
		try {
			booksInventoryRepository.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/books/googlebooks/{name}")
	public ResponseEntity<List<Book>> searchBooksInGoogleBookAPI(@PathVariable("name") String name) {
		List<Book> books = new ArrayList<Book>();
		booksInventoryRepository.findAll().forEach(books::add);

		Map<String, Book> booksInInventory = BookHelper.rearrangeDataFormat(books);

		Map<String, String> booksFromGoogleService = googleSearchService.searchGoogleBookServiceBy(name);

		List<Book> bookData = BookHelper.calculateInventoryBasedOnGoogleBooksData(booksFromGoogleService,
				booksInInventory);
		if (bookData != null) {
			return new ResponseEntity<>(bookData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
