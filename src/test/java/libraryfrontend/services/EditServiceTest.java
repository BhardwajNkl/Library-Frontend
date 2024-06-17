package libraryfrontend.services;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

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
public class EditServiceTest {
	@Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private EditService editService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetBook() {
    	
    	// arrange
    	Author authorMock = new Author("1","John Mcafee");
        Book mockBook = new Book(1,"code1","book1","Monday, June 10, 2022", authorMock);
        
        // mock
        when(restTemplate.getForObject("http://localhost:9090/book/1", Book.class)).thenReturn(mockBook);
        
        // act
        Book result = editService.getBook("1");

        // assert and verify
        assertEquals(mockBook, result);
        verify(restTemplate).getForObject("http://localhost:9090/book/1", Book.class);
    }

    @Test
    public void testGetAuthorList() {
    	
    	// arrange
        Author[] authors = { new Author("1", "John Doe"), new Author("2", "Jane Smith") };
        List<Author> authorList = Arrays.asList(authors);

        // mock
        when(restTemplate.getForObject("http://localhost:9090/author", Author[].class)).thenReturn(authors);

        // act
        List<Author> result = editService.getAuthorList();

        // assert and verify
        assertEquals(authorList, result);
        verify(restTemplate).getForObject("http://localhost:9090/author", Author[].class);
    }
}
