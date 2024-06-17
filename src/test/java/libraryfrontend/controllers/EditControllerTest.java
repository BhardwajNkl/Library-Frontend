package libraryfrontend.controllers;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
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
import libraryfrontend.services.EditService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = 
{ "file:src/main/webapp/WEB-INF/libraryfrontend-servlet.xml",
		"file:src/main/webapp/WEB-INF/web.xml"})
@WebAppConfiguration
public class EditControllerTest {
 
    private MockMvc mockMvc;
 
    @Mock
    private HttpServletRequest request;
 
    @Mock
    private MockHttpSession session;
 
    @Mock
    private EditService editService;
 
    @InjectMocks
    private EditController editController;
 
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(editController);
        mockMvc = MockMvcBuilders.standaloneSetup(editController).build();
    }
 
    @Test
    public void testEditControllerForLoggedInUser() throws Exception {
    	// arrange
    	Author author = new Author("1", "John Doe");
    	List<Author> authorList = Arrays.asList(author);
        
        Book mockBook = new Book(1,"code1","book1","Monday, June 10, 2022", author);
    	
        String loggedUser = "root";
        
        // mock
     	when(editService.getAuthorList()).thenReturn(authorList);
     	when(editService.getBook("1")).thenReturn(mockBook);
     	
    	when(session.getAttribute("loggeduser")).thenReturn(loggedUser);
    	
    	// act and verify
  
      mockMvc.perform(get("/edit")
    		  .param("id", "1")
    		  .session(session))
              .andExpect(status().isOk())
              .andExpect(view().name("edit_page"))
              .andExpect(model().attributeExists("authorList"))
              .andExpect(model().attribute("authorList", authorList))
              .andExpect(model().attributeExists("book"))
              .andExpect(model().attribute("book", mockBook));
      
        verify(editService).getAuthorList();
        verify(editService, times(1)).getBook("1");
    }
    
    
    @Test
    public void testEditControllerForUserWithoutLogin() throws Exception {
    	
    	// mock
    	when(session.getAttribute("loggeduser")).thenReturn(null);
  
    	// act and verify
    	mockMvc.perform(get("/edit")
    		  .param("id", "1")
      		  .session(session))
  		      .andExpect(status().is3xxRedirection()) 
  		      .andExpect(redirectedUrl("/"));
    	
    	verify(editService, never()).getAuthorList();
		verify(editService, never()).getBook(anyString());
    }
 
    
}
