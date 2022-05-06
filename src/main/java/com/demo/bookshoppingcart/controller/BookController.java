package com.demo.bookshoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bookshoppingcart.entity.Book;
import com.demo.bookshoppingcart.service.BookService;

@RestController
@RequestMapping("api/books")
public class BookController {

	@Autowired
	BookService bookService;
	
	@GetMapping("/getBooks")
	public List<Book> getBooks()
	{
		return bookService.getBooks();
	}
	
	@PostMapping("/addBook")
	public Book addBook(@RequestBody Book book)
	{
		
		return bookService.addbook(book) ;	
	}
}
