package libraryfrontend.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import libraryfrontend.models.UserCredentialsModel;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private LoginService loginService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testVerifyUserCredentialsSuccessful() {
		// arrange
    	String username = "root";
        String password = "root";
        UserCredentialsModel credentials = new UserCredentialsModel(username, password);

        String url = "http://localhost:9090/verify-login-credentials";
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<UserCredentialsModel> request = new HttpEntity<UserCredentialsModel>(credentials, headers);

        ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>(true, HttpStatus.OK);

        // mock
        when(restTemplate.postForEntity(eq(url), any(HttpEntity.class), eq(Boolean.class))).thenReturn(responseEntity);

        // act and assert
        boolean result = loginService.verifyUserCredentials(username, password);
        assertTrue(result);
    }

    @Test
    public void testVerifyUserCredentialsFailure() {
    	// arrange
        String username = "root";
        String password = "wrongroot";
        UserCredentialsModel credentials = new UserCredentialsModel(username, password);

        String url = "http://localhost:9090/verify-login-credentials";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<UserCredentialsModel> request = new HttpEntity<UserCredentialsModel>(credentials, headers);

        ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>(false, HttpStatus.OK);

        // mock
        when(restTemplate.postForEntity(eq(url), any(HttpEntity.class), eq(Boolean.class))).thenReturn(responseEntity);

        // act and assert
        boolean result = loginService.verifyUserCredentials(username, password);
        assertFalse(result);
    }
}