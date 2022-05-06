package com.demo.bookshoppingcart.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.bookshoppingcart.entity.Book;
import com.demo.bookshoppingcart.service.BookService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
	
	private final int id = 1234;
	private final String author="author";
	private final String title="title";
	private final double price = 555;
	
//	private static ObjectMapper mapper = new ObjectMapper();
	
	Book mockbook = new Book(id, title, author, price);
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;

	@Test
	public void getBooksTest() throws Exception
	{
		List<Book> bookList = new ArrayList<>();
		bookList.add(mockbook);
		
		Mockito.when(bookService.getBooks()).thenReturn(bookList);
		/*
		 mockMvc.perform(get("/api/books/getBooks"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$", Matchers.hasSize(1)))
         .andExpect(jsonPath("$[0].name", Matchers.is("name")));
         */
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books/getBooks").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		String expected = "[{id:1234,title:title,author:author,price:555.0}]";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	
}
