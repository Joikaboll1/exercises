package com.pluralsight.client;


import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.json.JSONObject;

import com.pluralsight.model.Activity;
import com.pluralsight.model.ExactMatchDto;

public class ActivitySearchClient {

	private Client client;
	
	public ActivitySearchClient() {
		client =  ClientBuilder.newClient();
	}
	
	public List<Activity> search(String param, String searchValue, ExactMatchDto exactMatchDto, String param2, int durationFrom, String param3, int durationTo){
		
		URI uri = UriBuilder.fromUri("http://localhost:8080/exercise-services/webapi")
				.path("search/activities")
				.queryParam(param, searchValue)
				.queryParam(param2, durationFrom)
				.queryParam(param3, durationTo)
				.build();
		WebTarget target = client.target(uri);
		System.out.println("POST to url: " + uri);		
		List<Activity> response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(exactMatchDto, MediaType.APPLICATION_JSON), new GenericType<List<Activity>>() {} );
//		List<Activity> response = target.request(MediaType.APPLICATION_JSON).post(faem);

		
		return response;
		
	}
	
}
