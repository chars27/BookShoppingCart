package com.demo.bookshoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bookshoppingcart.entity.Book;
import com.demo.bookshoppingcart.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;
	
	
	@Override
	public List<Book> getBooks() {
		
		return bookRepository.findAll();
	}


	@Override
	public Book addbook(Book book) {
		
		return bookRepository.save(book); 
	}


	@Override
	public Book updateBook(Integer id, Book book) {
		
		book.setId(id);
		return bookRepository.save(book);
	}

}
