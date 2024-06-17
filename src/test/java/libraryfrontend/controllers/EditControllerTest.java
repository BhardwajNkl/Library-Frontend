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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import libraryfrontend.models.Author;
import libraryfrontend.models.Book;
import libraryfrontend.services.EditService;

@RunWith(MockitoJUnitRunner.class)
public class EditControllerTest {

    @Mock
    private EditService editService;

    @Mock
    private HttpSession session;

    @InjectMocks
    private EditController editController;

    @Before
    public void setUp() {
        when(session.getAttribute("loggeduser")).thenReturn("user123");
    }

    @Test
    public void testEdit_WithLoggedUser() {
    	Author author1 = new Author("1","John Mcafee");
    	Author author2 = new Author("2", "Author2");
//    	mockAuthorList.add(new Author("1", "Author1"));
//        mockAuthorList.add(new Author("2", "Author2"));
    	List<Author> mockAuthorList = Arrays.asList(author1, author2);
    	
        // Mock data for book and author list
        Book mockBook = new Book(1,"code1", "book1","Monday, June 10, 2022", author1);

        // Mocking EditService behavior
        when(editService.getBook("1")).thenReturn(mockBook);
        when(editService.getAuthorList()).thenReturn(mockAuthorList);

        // Call the edit method
        ModelAndView modelAndView = editController.edit("1", session);

        // Verify session attribute access
        verify(session).getAttribute("loggeduser");

        // Verify the view name
        assertEquals("edit", modelAndView.getViewName());

        // Verify that Book is added to ModelAndView
        Book actualBook = (Book) modelAndView.getModel().get("book");
        assertNotNull(actualBook);
        assertEquals(mockBook, actualBook);

        // Verify that Author list is added to ModelAndView
        List<Author> actualAuthorList = (List<Author>) modelAndView.getModel().get("authorList");
        assertNotNull(actualAuthorList);
        assertEquals(2, actualAuthorList.size());
    }

    @Test
    public void testEdit_WithoutLoggedUser() {
        // Mocking HttpSession behavior
        when(session.getAttribute("loggeduser")).thenReturn(null);

        // Call the edit method
        ModelAndView modelAndView = editController.edit("1", session);

        // Verify that redirect happens if loggeduser is null
        assertEquals("redirect:/", modelAndView.getViewName());

        // Verify that EditService.getBook() and EditService.getAuthorList() are not called
        verify(editService, never()).getBook(anyString());
        verify(editService, never()).getAuthorList();
    }
}