package services;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Author;
import model.Book;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
public class HomeService {
	public List<Book> getData() {
		//let's call the api to get books list
		String url = "http://localhost:9090/book";
		HttpRequest hr = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
		HttpClient hc = HttpClient.newBuilder().build();
		HttpResponse<String> response = null;
		try {
			response = hc.send(hr, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		System.out.println(response.statusCode());
		
		String jsonData= response.body();
		
		ObjectMapper mapper = new ObjectMapper();
		List<Book> bookList = null;
		try {
			bookList=mapper.readValue(jsonData, new TypeReference<List<Book>>(){});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return bookList;
	}
}
