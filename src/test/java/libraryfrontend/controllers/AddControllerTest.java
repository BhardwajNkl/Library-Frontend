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
import libraryfrontend.services.AddService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = 
{ "file:src/main/webapp/WEB-INF/libraryfrontend-servlet.xml",
		"file:src/main/webapp/WEB-INF/web.xml"})
@WebAppConfiguration
public class AddControllerTest {
 
    private MockMvc mockMvc;
 
    @Mock
    private HttpServletRequest request;
 
    @Mock
    private MockHttpSession session;
 
    @Mock
    private AddService addService;
 
    @InjectMocks
    private AddController addController;
 
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(addController);
        mockMvc = MockMvcBuilders.standaloneSetup(addController).build();
    }
 
    @Test
    public void testAddControllerForLoggedInUser() throws Exception {
    	
    	// arrange
    	Author author = new Author("1", "John Doe");
    	List<Author> authorList = Arrays.asList(author);
    	String loggedUser = "root";

    	// mock
     	when(addService.getAuthorList()).thenReturn(authorList);
    	when(session.getAttribute("loggeduser")).thenReturn(loggedUser);
  
    	// act and verify
		mockMvc.perform(get("/add").session(session))
		  .andExpect(status().isOk())
		  .andExpect(view().name("add_page"))
		  .andExpect(model().attributeExists("authorList"))
		  .andExpect(model().attribute("authorList", authorList));
		
		verify(addService).getAuthorList();
    }
    
    @Test
    public void testAddControllerForUserWithoutLogin() throws Exception {
    	
    	// mock
    	when(session.getAttribute("loggeduser")).thenReturn(null);
  
    	// act and verify
    	mockMvc.perform(get("/add")
      		  .session(session))
  		      .andExpect(status().is3xxRedirection()) 
  		      .andExpect(redirectedUrl("/"));
		
		verify(addService, never()).getAuthorList();
    }
 
    
}


