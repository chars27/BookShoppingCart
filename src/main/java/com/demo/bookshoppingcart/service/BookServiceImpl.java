package com.demo.bookshoppingcart.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bookshoppingcart.entity.Book;
import com.demo.bookshoppingcart.repository.BookRepository;

@Service
@Transactional
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
	public Book updateBook(Integer id, Book book) throws Exception {
		
		Book bookToBeUpdated = bookRepository.findById(id).orElseThrow(()-> new Exception("Book with"+id+"not found"));		
		book.setId(bookToBeUpdated.getId());
		return bookRepository.save(book);
	}



	@Override
	public void deleteBookById(Integer id) throws Exception {
	
		Book bookToBeDeleted = bookRepository.findById(id).orElseThrow(()-> new Exception("Book with"+id+"not found"));
		bookRepository.deleteById(bookToBeDeleted.getId());
	}

	@Override
	public double getTotalBookPrice(List<Integer> books) {
		//double finalPrice=0;
		AtomicReference<Double> finalPrice = new AtomicReference<Double>(0.0);
		books.forEach(id->{
			Optional<Book> ids = bookRepository.findById(id);
			ids.ifPresent(book ->{
				finalPrice.updateAndGet(v->v+(book.getPrice()));
			});
			
		});
		return finalPrice.get();
	}

	

}
