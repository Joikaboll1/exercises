package com.pluralsight.repository;

import java.util.List;
import java.util.ArrayList;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;

public class ActivityRepositoryStub implements ActivityRepository {

	private List<Activity> activities;
	
	
	
	public ActivityRepositoryStub() {
		this.activities = new ArrayList<Activity>();
		
		Activity act1 = new Activity();
		User u = new User();
		u.setId("5555");
		u.setName("JONAS");
		act1.setUser(u);
		act1.setId("1234");
		act1.setDescription("Swimming");
		act1.setDuration(55);
		
		activities.add(act1);
		
		Activity act2 = new Activity();
		User u2 = new User();
		u2.setId("5535");
		u2.setName("JENS");
		act2.setUser(u2);
		act2.setId("2234");
		act2.setDescription("Cycling");
		act2.setDuration(120);
		
		activities.add(act2);
	}

	/* (non-Javadoc)
	 * @see com.pluralsight.repository.ActivityRepository#findAllActivities()
	 */

	public void addActivity(Activity a) 
	{
		this.activities.add(a);
	}
	
	@Override
	public List<Activity> findAllActivities()
	{
		return activities;
	}

	
	private Activity searchForActivity(String activityId) 
	{
		for(int i=0;i<this.activities.size();i++) 
		{
			if(activities.get(i).getId().equals(activityId)) {
				System.out.println(activities.get(i).getId());
				return activities.get(i);
			}
		}
		return null;
	}
	@Override
	public Activity findActivity(String activityId) {
		
		if(activityId.equals("7777")) {
			return null;
		}
		
//		System.out.println(activityId);
		Activity a = searchForActivity(activityId);		
		return a;
	}

}
