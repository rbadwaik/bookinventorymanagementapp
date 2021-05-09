package com.bookinventorymanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookinventorymanagementapp.model.Book;

public interface BooksInventoryRepository extends JpaRepository<Book, String> {
	
	Book findByNameContaining(String name);
}
