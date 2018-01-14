package com.pluralsight.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;

public class ActivityRepositoryStub implements ActivityRepository {

	private List<Activity> activities;
	
	
	
	@Override
	public List<Activity> findByDescription(String descriptions, String exactMatch,int from, int to) {	
		
		
		
		List<Activity> results = new ArrayList<Activity>();
		String [] descriptionsArray;
		
		System.out.println("from->" +from);
		System.out.println("to->" +to);
		System.out.println("sss->" +descriptions);
		
		descriptions.replaceAll("\\s+", "");
		descriptionsArray = descriptions.split(",");
		
		for (String s:descriptionsArray) {
			System.out.println("-->" + s);
			for(Activity a: activities) {
				if(exactMatch.equalsIgnoreCase("true")) {
					if(a.getDescription().toLowerCase().equals(s.toLowerCase()) && (a.getDuration() > from && a.getDuration() < to))
						results.add(a);	
				}else {
					if(a.getDescription().toLowerCase().contains(s.toLowerCase()) && (a.getDuration() > from && a.getDuration() < to))
						results.add(a);		
				}
				
			}
			
			
			
		}
		return results;
		
	}
	



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
		Random random = new Random();
		int id = random.nextInt(9000) + 1000;
		a.setId("" + id);
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

	@Override
	public Activity updateActivity(String id, Activity activity) {
		// TODO Auto-generated method stub
		Activity a = searchForActivity(id);
		if(a != null) {
			a.setDescription(activity.getDescription());
			a.setDuration(activity.getDuration());
			return a;
		}

		
		return null;
	}

	@Override
	public boolean deleteActivity(String activityId) {
		// TODO Auto-generated method stub
		Activity a = findActivity(activityId);
		if(a != null) {
			activities.remove(a);
			return true;
		}
		return false;
		
	}

}
