package libraryfrontend.services;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import libraryfrontend.models.Author;
import libraryfrontend.models.Book;

@RunWith(MockitoJUnitRunner.class)
public class HomeServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private HomeService homeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetBookList() {
    	Author author = new Author("1","John Mcafee");
        Book[] books = { new Book(1,"code1","book1","Monday, June 10, 2022", author),
        		new Book(2,"code2","book2","Monday, June 10, 2022", author) };
        List<Book> bookList = Arrays.asList(books);

        // Mock RestTemplate behavior
        when(restTemplate.getForObject("http://localhost:9090/book", Book[].class)).thenReturn(books);

        // Call the method under test
        List<Book> result = homeService.getBookList();

        // Verify that restTemplate.getForObject() was called with the correct URL and response type
        verify(restTemplate).getForObject("http://localhost:9090/book", Book[].class);

        // Verify the expected result
        assertEquals(bookList, result);
    }
}