package libraryfrontend.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class BookTest {

    private Book book;
    private Author author;

    @Before
    public void setUp() {
        author = new Author("1", "John Mcafee");
        book = new Book();
    }

    @Test
    public void testNoArgumentsConstructor() {
        Book book = new Book();
        assertNotNull(book);
    }

    @Test
    public void testAllArgumentsConstructor() {
        int bookId = 1;
        String bookCode = "code1";
        String bookName = "book1";
        String dateAdded = "Monday, June 10, 2022";

        Book book = new Book(bookId, bookCode, bookName, dateAdded, author);
        
        assertNotNull(book);
        assertEquals(bookId, book.getBookId());
        assertEquals(bookCode, book.getBookCode());
        assertEquals(bookName, book.getBookName());
        assertEquals(dateAdded, book.getDateAdded());
        assertEquals(author, book.getAuthor());
    }

    @Test
    public void testGettersAndSetters() {
    	int bookId = 1;
        String bookCode = "code1";
        String bookName = "book1";
        String dateAdded = "Monday, June 10, 2022";

        book.setBookId(bookId);
        book.setBookCode(bookCode);
        book.setBookName(bookName);
        book.setDateAdded(dateAdded);
        book.setAuthor(author);

        assertEquals(bookId, book.getBookId());
        assertEquals(bookCode, book.getBookCode());
        assertEquals(bookName, book.getBookName());
        assertEquals(dateAdded, book.getDateAdded());
        assertEquals(author, book.getAuthor());
    }
}