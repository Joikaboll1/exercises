package com.pluralsight;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;
import com.pluralsight.repository.ActivityRepository;
import com.pluralsight.repository.ActivityRepositoryStub;

@Path("activities")
public class ActivityResource {

	private ActivityRepository activityRepository = new ActivityRepositoryStub();
		
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Activity> getAllActivities() // http://localhost:8080/exercise-services/webapi/activities
	{
		return activityRepository.findAllActivities();
	}	
	@GET
	@Path("{activityId}")
	@Produces(MediaType.APPLICATION_JSON) // http://localhost:8080/exercise-services/webapi/activities/2234
	public Response getActivity(@PathParam ("activityId") String activityId) {
		 try 
		 {
			 if(activityId == null || activityId.length() != 4 ) {
					return Response.status(Status.BAD_REQUEST).build();
				}
				
				Activity activity = activityRepository.findActivity(activityId);
				
				if(activity == null) {
					return Response.status(Status.NOT_FOUND).build();
				}
				
				System.out.println("Getting activity ID: " + activityId);		
				return Response.ok().entity(activity).build(); 
		 }
		 catch(Exception e) {
			 
		 }
		 
		 return Response.status(Status.BAD_REQUEST).build();		
	}
	
	@GET
	@Path("{activityId}/user")
	@Produces(MediaType.APPLICATION_JSON) // http://localhost:8080/exercise-services/webapi/activities/2234/user
	public User getUser(@PathParam ("activityId") String activityId) {
		System.out.println(activityId);
		return activityRepository.findActivity(activityId).getUser();
	}
	
	@POST
	@Path("activity")
	@Consumes(MediaType.APPLICATION_JSON) // http://localhost:8080/exercise-services/webapi/activities/activity
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createActivity(Activity activity) 
	{
		
		activityRepository.addActivity(activity);
		
		return Response.ok(activity).build();
	}
	
	
	@PUT
	@Path("{activityId}")
	@Consumes(MediaType.APPLICATION_JSON) // http://localhost:8080/exercise-services/webapi/activities/{activityId}
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateActivity(@PathParam ("activityId") String activityId,Activity activity) {
		
		
		Activity a = activityRepository.updateActivity(activityId,activity);
		
		return Response.ok(a).build();
	}
	
	
	
//	@POST
//	@Path("activity")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})	
//	public Response addActivity(MultivaluedMap<String,String> formParams) 
//	{
//		System.out.println(formParams.getFirst("description"));
//		System.out.println(formParams.getFirst("duration"));
//		
//		Activity activity = new Activity();
//		activity.setDescription(formParams.getFirst("description"));
//		activity.setDuration(Integer.parseInt(formParams.getFirst("duration")));
//		User u2 = new User();
//		u2.setId("6677");
//		u2.setName("dasdas");
//		activity.setUser(u2);
//
//		
//		activityRepository.addActivity(activity);
//		
//		return Response.ok(activity).build();
//	}
	
}
