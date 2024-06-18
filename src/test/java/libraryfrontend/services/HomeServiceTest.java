package libraryfrontend.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @Test
    public void testGetBookList() {
    	// arrange
    	Author author = new Author("1","author1");
        Book[] books = { new Book(1,"code1","book1","Monday, June 10, 2022", author),
        		new Book(2,"code2","book2","Monday, June 10, 2022", author) };
        List<Book> bookList = Arrays.asList(books);

        // mock
        when(restTemplate.getForObject("http://localhost:9090/book", Book[].class)).thenReturn(books);

        // act
        List<Book> result = homeService.getBookList();

        // assert and verify
        assertEquals(bookList, result);
        verify(restTemplate).getForObject("http://localhost:9090/book", Book[].class);
    }
}