package libraryfrontend.services;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import libraryfrontend.models.Author;

@RunWith(MockitoJUnitRunner.class)
public class AddServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private AddService addService;

    @Test
    public void testGetAuthorList() {
        Author[] authors = { new Author("1", "John Doe"), new Author("2", "Jane Smith") };
        List<Author> authorList = Arrays.asList(authors);

        when(restTemplate.getForObject(anyString(), eq(Author[].class))).thenReturn(authors);

        List<Author> result = addService.getAuthorList();

        verify(restTemplate).getForObject("http://localhost:9090/author", Author[].class);

        assertEquals(authorList, result);
    }
}