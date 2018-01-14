package com.pluralsight;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.json.*;


import com.pluralsight.model.Activity;
import com.pluralsight.model.ExactMatchDto;
import com.pluralsight.repository.ActivityRepository;
import com.pluralsight.repository.ActivityRepositoryStub;

@Path("search/activities")
public class ActivitySearchResource {

	private ActivityRepository activityRepository = new ActivityRepositoryStub();
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response search(@QueryParam(value = "description") String descriptions,
						   @QueryParam(value = "durationfrom") int durationFrom,
						   @QueryParam(value = "durationto") int durationTo,
						   ExactMatchDto inBody) {
		
		System.out.println(descriptions + ", " + durationFrom + " - " + durationTo);
		if(durationFrom <= 0) {
			durationFrom = 0;
		}
		if(durationTo <= 0) {
			durationTo = 9999;
		}
		JSONObject JSONexactMatch= new JSONObject(inBody);
		String exactMatch = JSONexactMatch.optString("exactMatch");
		System.out.println("---" + exactMatch + "---");
		if(descriptions.isEmpty()) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		List<Activity> activities = activityRepository.findByDescription(descriptions,exactMatch,durationFrom,durationTo);		
		
		return Response.ok().entity(new GenericEntity<List<Activity>>(activities){}).build();

		
	}
}
