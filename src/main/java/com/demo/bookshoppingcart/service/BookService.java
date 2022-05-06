package com.demo.bookshoppingcart.service;

import java.util.List;

import com.demo.bookshoppingcart.entity.Book;

public interface BookService {
	
	public List<Book> getBooks();

	public Book addbook(Book book);

}
