/**
 * 
 */
package libraryfrontend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import libraryfrontend.models.UserCredentialsModel;


/**
 * @author nikhilbhardwaj01
 *
 */
@Service
public class LoginService {
	@Autowired
	private RestTemplate restTemplate;
	
	public boolean verifyUserCredentials(String username, String password) {
		String url = "http://localhost:9090/verify-login-credentials";
		
        UserCredentialsModel credentials = new UserCredentialsModel(username, password);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<UserCredentialsModel> request = new HttpEntity<UserCredentialsModel>(credentials, headers);

        ResponseEntity<Boolean> response = restTemplate.postForEntity(url, request, Boolean.class);
        return response.getBody();
	}
}
