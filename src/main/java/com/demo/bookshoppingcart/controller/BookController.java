package com.demo.bookshoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		List<Book> booksList = bookService.getBooks();
		//return new ResponseEntity<List<Book>>(booksList,HttpStatus.OK);
		return booksList;
	}
	
	@PostMapping("/addBook")
	public Book addBook(@RequestBody Book book)
	{
		
		return bookService.addbook(book) ;	
	}
	
	@PutMapping("/updateBook/{id}")
	public Book updateBook(@PathVariable("id") Integer id, @RequestBody Book book) throws Exception
	{
		Book updatedBook=bookService.updateBook(id,book);
		return updatedBook;	
	}
	
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable("id") Integer id) throws Exception
	{
		bookService.deleteBookById(id);
		return new ResponseEntity<String>("Book deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/checkout")
	public double getTotalBookPrice(@RequestParam("books") List<Integer> books)
	{
		return bookService.getTotalBookPrice(books);	
	}
	
}
