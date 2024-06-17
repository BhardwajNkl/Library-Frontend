package libraryfrontend.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class UserCredentialsModelTest {

    private UserCredentialsModel userCredentials;

    @Before
    public void setUp() {
        userCredentials = new UserCredentialsModel();
    }

    @Test
    public void testNoArgumentsConstructor() {
        UserCredentialsModel userCredentials = new UserCredentialsModel();
        assertNotNull(userCredentials);
    }

    @Test
    public void testAllArgumentsConstructor() {
        String username = "root";
        String password = "root";
        
        UserCredentialsModel userCredentials = new UserCredentialsModel(username, password);
        assertNotNull(userCredentials);
        assertEquals(username, userCredentials.getUsername());
        assertEquals(password, userCredentials.getPassword());
    }

    @Test
    public void testGettersAndSetters() {
        String username = "root";
        String password = "root";
        
        userCredentials.setUsername(username);
        userCredentials.setPassword(password);
        
        assertEquals(username, userCredentials.getUsername());
        assertEquals(password, userCredentials.getPassword());
    }
}