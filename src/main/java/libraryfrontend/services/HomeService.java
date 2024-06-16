package libraryfrontend.services;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import libraryfrontend.models.Book;

@Service
public class HomeService {
	@Autowired
	private RestTemplate restTemplate;		
	
	public List<Book> getBookList() {
		String url = "http://localhost:9090/book";
		
        Book[] bookArray = restTemplate.getForObject(url, Book[].class);

        List<Book> bookList = Arrays.asList(bookArray);
        
		return bookList;
	}
}
