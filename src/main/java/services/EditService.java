package services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Author;
import model.Book;

public class EditService {
	public Book getBook(String id) {
		//let's call the api
		String url = "http://localhost:9090/book/"+id;
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
		String jsonData= response.body();
		ObjectMapper mapper = new ObjectMapper();
		Book book = null;
		try {
			book=mapper.readValue(jsonData, Book.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return book;
}
	
	public List<Author> getAuthorList() {
		String url = "http://localhost:9090/author";
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
		
		String jsonData= response.body();
		
		ObjectMapper mapper = new ObjectMapper();
		List<Author> authorList = null;
		try {
			authorList=mapper.readValue(jsonData, new TypeReference<List<Author>>(){});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return authorList;
}
}
