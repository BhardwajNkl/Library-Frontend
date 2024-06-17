package libraryfrontend.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import libraryfrontend.models.Author;
import libraryfrontend.models.Book;
import libraryfrontend.services.HomeService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = 
{ "file:src/main/webapp/WEB-INF/libraryfrontend-servlet.xml",
		"file:src/main/webapp/WEB-INF/web.xml"})
@WebAppConfiguration
public class HomeControllerTest {
 
    private MockMvc mockMvc;
 
    @Mock
    private HttpServletRequest request;
 
    @Mock
    private MockHttpSession session;
 
    @Mock
    private HomeService homeService;
 
    @InjectMocks
    private HomeController homeController;
 
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(homeController);
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }
 
    @Test
    public void testDeleteBook() throws Exception {
    	
    	Author[] authors = { new Author("1", "John Doe"), new Author("2", "Jane Smith") };
        List<Author> authorList = Arrays.asList(authors);
        
        Book mockBook = new Book(1,"code1","book1","Monday, June 10, 2022", authors[0]);
    	List<Book> bookList = Arrays.asList(mockBook);
    	
 
        // Mock request parameters
    	when(homeService.getBookList()).thenReturn(bookList);
 
        // Mock session
    	when(session.getAttribute("loggeduser")).thenReturn("user");
 
        // Mock retrieveBooks method
 
        // Perform the request
//    	  Perform the request and verify the result: CONTROLLER URL AND VIEW NAME SHOULD BE DIFFERENT TO AVOID ERROR IN TEST
      mockMvc.perform(get("/home").session(session))
              .andExpect(status().isOk())
              .andExpect(view().name("home"));
//              .andExpect(Model().attribute("authorList", mockAuthorList));
        // Verify interactions
        verify(homeService, times(1)).getBookList();
    }
 
    
}
