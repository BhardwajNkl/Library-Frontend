package libraryfrontend.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
    public void testVerifyUserCredentials_Success() {
		
    	String username = "user";
        String password = "pass";
        String url = "http://localhost:9090/verify-login-credentials";

        UserCredentialsModel credentials = new UserCredentialsModel(username, password);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<UserCredentialsModel> request = new HttpEntity<UserCredentialsModel>(credentials, headers);

        ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>(true, HttpStatus.OK);

        when(restTemplate.postForEntity(eq(url), any(HttpEntity.class), eq(Boolean.class))).thenReturn(responseEntity);

        boolean result = loginService.verifyUserCredentials(username, password);
        assertTrue(result);
    }

    @Test
    public void testVerifyUserCredentials_Failure() {
        String username = "user";
        String password = "wrongpass";
        String url = "http://localhost:9090/verify-login-credentials";

        UserCredentialsModel credentials = new UserCredentialsModel(username, password);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<UserCredentialsModel> request = new HttpEntity<UserCredentialsModel>(credentials, headers);

        ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>(false, HttpStatus.OK);

        when(restTemplate.postForEntity(eq(url), any(HttpEntity.class), eq(Boolean.class))).thenReturn(responseEntity);

        boolean result = loginService.verifyUserCredentials(username, password);
        assertFalse(result);
    }
}