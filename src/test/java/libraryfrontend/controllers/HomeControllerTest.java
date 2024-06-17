package libraryfrontend.controllers;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
    public void testHomeControllerForLoggedInUser() throws Exception {
    	// arrange
    	Author author = new Author("1", "John Doe");
        
        Book mockBook = new Book(1,"code1","book1","Monday, June 10, 2022", author);
    	List<Book> bookList = Arrays.asList(mockBook);
    	
    	String loggedUser = "root";
    	
    	// mock
     	when(homeService.getBookList()).thenReturn(bookList);
     	
    	when(session.getAttribute("loggeduser")).thenReturn(loggedUser);
  
    	// act and verify
    	mockMvc.perform(get("/home").session(session))
              .andExpect(status().isOk())
              .andExpect(view().name("home_page"))
              .andExpect(model().attributeExists("bookList"))
              .andExpect(model().attribute("bookList", bookList));
        verify(homeService).getBookList();
    }
    
    @Test
    public void testHomeControllerForUserWithoutLogin() throws Exception {
    	
    	// mock
    	when(session.getAttribute("loggeduser")).thenReturn(null);
  
    	// act and verify
    	mockMvc.perform(get("/home")
      		  .session(session))
  		      .andExpect(status().is3xxRedirection()) 
  		      .andExpect(redirectedUrl("/"));
		
		verify(homeService, never()).getBookList();
    }
 
    
}
