package com.pluralsight.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.pluralsight.model.Activity;

public class ActivityClientTest {

	
	
	
	@Test
	public void testPut() {
		Activity activity = new Activity();
		
		activity.setId("1234");
		activity.setDescription("dasdasdas");
		activity.setDuration(60);
		
		ActivityClient client = new ActivityClient();
		activity = client.update(activity);
		
		System.out.println(activity.getId());
		System.out.println(activity.getDescription());
		System.out.println(activity.getDuration());
		
		
		assertTrue(activity.getDescription().equals("dasdasdas"));
		assertNotNull(activity);

		
	}
	
	@Test 
	public void testCreate() {
		ActivityClient client = new ActivityClient();
		
		Activity activity = new Activity();
		activity.setDescription("Swimming");
		activity.setDuration(90);
		
		activity = client.create(activity);
		
		assertNotNull(activity);
			
		
	}
	
	@Test
	public void testGet() {
		ActivityClient client = new ActivityClient();
		
		Activity activity = client.get("1234");
		
		System.out.println(activity);
		
		assertNotNull(activity);
	}
	
	@Test
	public void testGetList() {
		ActivityClient client = new ActivityClient();
		
		List<Activity> activities = client.get();
				
		System.out.println(activities);
		
		assertNotNull(activities);
	}

	@Test (expected=RuntimeException.class)
	public void testWithBadRequestException() {
		ActivityClient client = new ActivityClient();		
		client.get("123");

	}
	
	@Test (expected=RuntimeException.class)
	public void testWithNotFoundException() {
		ActivityClient client = new ActivityClient();		
		client.get("7777");

	}
	



}
