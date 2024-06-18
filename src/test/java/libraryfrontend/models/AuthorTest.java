package libraryfrontend.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class AuthorTest {

    private Author author;

    @Before
    public void setUp() {
        author = new Author();
    }

    @Test
    public void testNoArgumentsConstructor() {
        Author author = new Author();
        assertNotNull(author);
    }

    @Test
    public void testAllArgumentsConstructor() {
        String authorId = "1";
        String authorName = "author1";
        Author author = new Author(authorId, authorName);
        assertNotNull(author);
        assertEquals(authorId, author.getAuthorId());
        assertEquals(authorName, author.getAuthorName());
    }

    @Test
    public void testGettersAndSetters() {
        String authorId = "1";
        String authorName = "author1";
        
        author.setAuthorId(authorId);
        author.setAuthorName(authorName);
        
        assertEquals(authorId, author.getAuthorId());
        assertEquals(authorName, author.getAuthorName());
    }
}