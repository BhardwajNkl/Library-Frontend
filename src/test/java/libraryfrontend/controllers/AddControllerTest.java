package libraryfrontend.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import libraryfrontend.models.Author;
import libraryfrontend.services.AddService;

//@RunWith(MockitoJUnitRunner.class)
//public class AddControllerTest {
//
//    @Mock
//    private AddService addService;
//
//    @Mock
//    private HttpSession session;
//
//    @InjectMocks
//    private AddController addController;
//
//    @Before
//    public void setUp() {
//        // Mocking the HttpSession
//        when(session.getAttribute("loggeduser")).thenReturn("user123");
//    }
//
//    @Test
//    public void testAdd_WithLoggedUser() {
//    	// Mock session to simulate logged in user
////        when(session.getAttribute("loggeduser")).thenReturn("user123");
//
//        // Mock data for author list
//        List<Author> mockAuthorList = new ArrayList<Author>();
//        mockAuthorList.add(new Author("1","Author1"));
//        mockAuthorList.add(new Author("2","Author2"));
//
//        // Mocking AddService behavior
//        when(addService.getAuthorList()).thenReturn(mockAuthorList);
//
//        // Call the add method
//        ModelAndView modelAndView = addController.add(session);
//
//        // Verify session attribute access
//        verify(session).getAttribute("loggeduser");
//
//        // Verify the view name
//        assertEquals("add", modelAndView.getViewName());
//
//        // Verify that Author list is added to ModelAndView
//        List<Author> actualAuthorList = (List<Author>) modelAndView.getModel().get("authorList");
//        assertNotNull(actualAuthorList);
//        assertEquals(2, actualAuthorList.size());
//    }
//
//    @Test
//    public void testAdd_WithoutLoggedUser() {
//        // Mocking HttpSession behavior
//        when(session.getAttribute("loggeduser")).thenReturn(null);
//
//        // Call the add method
//        ModelAndView modelAndView = addController.add(session);
//
//        // Verify that redirect happens if loggeduser is null
//        assertEquals("redirect:/", modelAndView.getViewName());
//
//        // Verify that AddService.getAuthorList() is not called
//        verify(addService, never()).getAuthorList();
//    }
//}



// =================== USING MOCK MVC

@RunWith(MockitoJUnitRunner.class)
public class AddControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AddService addService;

    @Mock
    private MockHttpSession session;

    @InjectMocks
    private AddController addController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(addController).build();

        // Mocking the HttpSession
        when(session.getAttribute("loggeduser")).thenReturn("user123");
    }

    @Test
    public void testAdd_WithLoggedUser() throws Exception {
        // Mock data for author list
        List<Author> mockAuthorList = new ArrayList<Author>();
        mockAuthorList.add(new Author("1","Author1"));
        mockAuthorList.add(new Author("2","Author2"));

        // Mocking AddService behavior
        when(addService.getAuthorList()).thenReturn(mockAuthorList);

        // Perform the request and verify the result
        mockMvc.perform(get("/add").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("add"))
                .andExpect(model().attribute("authorList", mockAuthorList));
    }
}