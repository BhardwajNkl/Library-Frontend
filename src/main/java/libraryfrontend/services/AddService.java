package libraryfrontend.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import libraryfrontend.models.Author;

@Service
public class AddService {
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Author> getAuthorList() {
		String url = "http://localhost:9090/author";
		
        Author[] authorArray = restTemplate.getForObject(url, Author[].class);

        List<Author> authorList = Arrays.asList(authorArray);
        
		return authorList;
	}
}
