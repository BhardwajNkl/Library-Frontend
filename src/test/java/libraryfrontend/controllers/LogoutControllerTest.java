package libraryfrontend.controllers;

import static org.mockito.Mockito.verify;
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

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = 
{ "file:src/main/webapp/WEB-INF/libraryfrontend-servlet.xml",
		"file:src/main/webapp/WEB-INF/web.xml"})
@WebAppConfiguration
public class LogoutControllerTest {
 
    private MockMvc mockMvc;
 
    @Mock
    private HttpServletRequest request;
 
    @Mock
    private MockHttpSession session;
  
    @InjectMocks
    private LogoutController logoutController;
 
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(logoutController);
        mockMvc = MockMvcBuilders.standaloneSetup(logoutController).build();
    }
 
    @Test
    public void testLoginController() throws Exception {
    	  
      mockMvc.perform(get("/logout")
    		  .session(session))
		      .andExpect(status().is3xxRedirection()) 
		      .andExpect(redirectedUrl("/"));
      
        verify(session).invalidate();
    }
 
    
}

