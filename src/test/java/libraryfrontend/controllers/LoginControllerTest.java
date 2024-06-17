package libraryfrontend.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import libraryfrontend.services.LoginService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = 
{ "file:src/main/webapp/WEB-INF/libraryfrontend-servlet.xml",
		"file:src/main/webapp/WEB-INF/web.xml"})
@WebAppConfiguration
public class LoginControllerTest {
 
    private MockMvc mockMvc;
 
    @Mock
    private HttpServletRequest request;
 
    @Mock
    private MockHttpSession session;
 
    @Mock
    private LoginService loginService;
 
    @InjectMocks
    private LoginController loginController;
 
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(loginController);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }
 
    @Test
    public void testLoginSuccessful() throws Exception {
    	// arrange
    	String username = "root";
    	String password = "root";
    	
    	// mock
     	when(loginService.verifyUserCredentials(username, password)).thenReturn(true);
  
     	// act and verify
     	mockMvc.perform(get("/login")
    		  .param("username", username)
    		  .param("password", password)
    		  .session(session))
		      .andExpect(status().is3xxRedirection()) 
		      .andExpect(redirectedUrl("/home"));
      
        verify(loginService).verifyUserCredentials(username, password);
        verify(session).setAttribute("loggeduser", "root");
    }
    
    @Test
    public void testLoginFailed() throws Exception {
    	// arrange
    	String username = "root";
    	String password = "wrongroot";
    	
    	// mock
     	when(loginService.verifyUserCredentials(username, password)).thenReturn(false);
  
     	// act and verify
     	mockMvc.perform(get("/login")
    		  .param("username", username)
    		  .param("password", password)
    		  .session(session))
		      .andExpect(status().is3xxRedirection()) 
		      .andExpect(redirectedUrl("/"));
      
        verify(loginService).verifyUserCredentials(username, password);
    }
    
}

