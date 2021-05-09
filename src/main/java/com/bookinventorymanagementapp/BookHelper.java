package com.bookinventorymanagementapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookinventorymanagementapp.model.Book;

public class BookHelper {

	public static Map<String, Book> rearrangeDataFormat(List<Book> books) {
		Map<String, Book> bookIdToBook = new HashMap<String, Book>();

		for (Book book : books) {
			bookIdToBook.put(book.getId(), book);
		}

		return bookIdToBook;
	}

	public static List<Book> calculateInventoryBasedOnGoogleBooksData(Map<String, String> googleBooksdata,
			Map<String, Book> inventoryBooksData) {

		List<Book> result = new ArrayList<Book>();

		for (String bookId : googleBooksdata.keySet()) {
			if (inventoryBooksData.containsKey(bookId)) {
				result.add(inventoryBooksData.get(bookId));
			} else {
				Book book = new Book(bookId, googleBooksdata.get(bookId), 0);
				result.add(book);
			}
		}

		return result;
	}
}
