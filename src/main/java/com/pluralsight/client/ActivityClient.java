package com.pluralsight.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pluralsight.model.Activity;

public class ActivityClient {

	private Client client;
	
	
	public ActivityClient() {
		client = ClientBuilder.newClient();
	}
	
	public Activity get(String id) {
		
//		example: http://localhost:8080/exercise-services/webapi/activities/1234
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		
//		String response = target.path("activities/" + id).request(MediaType.APPLICATION_JSON).get(String.class);
		Response response = target.path("activities/" + id).request().get(Response.class);

		if(response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() +  ": There was an error on the server");
		}
		
		return response.readEntity(Activity.class);
	}
	
	public List<Activity> get(){
		
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		
		List<Activity> response = target.path("activities").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Activity>>() {} );
		
		return response;
	}

	public Activity create(Activity activity) {
		
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		
		
		Response response = target.path("activities/activity").request().post(Entity.entity(activity, MediaType.APPLICATION_JSON));
		
		if(response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() +  ": There was an error on the server");
		}
		
		return response.readEntity(Activity.class);
	}

	public Activity update(Activity activity) {
		// TODO Auto-generated method stub
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		
		Response response = target.path("activities/"+activity.getId().toString()).request().put(Entity.entity(activity, MediaType.APPLICATION_JSON));
		if(response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() +  ": There was an error on the server");
		}
		
		return response.readEntity(Activity.class);
	}

	public void delete(String id) {
		// TODO Auto-generated method stub
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		
		Response response = target.path("activities/" + id).request(MediaType.APPLICATION_JSON).delete();
		
		if(response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() +  ": There was an error on the server");
		}

	}	
}
